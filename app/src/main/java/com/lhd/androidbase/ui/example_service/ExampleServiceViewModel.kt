package com.lhd.androidbase.ui.example_service

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.await
import com.lhd.androidbase.base.viewmodel.BaseViewModel
import com.lhd.androidbase.data.models.Product
import com.lhd.androidbase.data.repositories.FakeStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ExampleServiceViewModel @Inject constructor(
    private val productRepository: FakeStoreRepository,
) :
    BaseViewModel() {

    private var _lsProduct = MutableLiveData<List<Product>?>()
    val lsProduct: LiveData<List<Product>?>
        get() = _lsProduct

    private val _progressWork = MutableLiveData<String>()
    val progressWork: LiveData<String>
        get() = _progressWork


    override fun fetchData() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val lsProduct = productRepository.getAllProduct()
            _lsProduct.postValue(lsProduct)
        }
        registerJobFinish()
    }

    fun exampleWorker(context: Context, lifecycleOwner: LifecycleOwner) {
        val myWorker = OneTimeWorkRequestBuilder<ExampleService>().build()
        WorkManager.getInstance(context).enqueue(myWorker)
        //
        WorkManager.getInstance(context).getWorkInfoByIdLiveData(myWorker.id)
            .observe(lifecycleOwner) {
                when (it.state) {
                    WorkInfo.State.SUCCEEDED -> {
                        _progressWork.value = it.outputData.getString(KEY_VALUE)
                    }

                    WorkInfo.State.FAILED -> {
                        _progressWork.value = it.outputData.getString(KEY_VALUE)
                    }

                    else -> {
                        _progressWork.value = ""
                    }
                }
            }
    }

    fun callMultipleApi() {
        viewModelScope.launch(handler) {
            val mergedRes = listOf(
                async { productRepository.getAProduct("1") },
                async { productRepository.getAllProduct() }
            ).awaitAll()

            Log.e("TAG", "callMultipleApi: $mergedRes")

        }

    }

    companion object {
        const val KEY_VALUE = "key_value"
    }

}