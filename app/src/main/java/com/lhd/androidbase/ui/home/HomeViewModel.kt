package com.lhd.androidbase.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lhd.androidbase.base.viewmodel.BaseViewModel
import com.lhd.androidbase.data.models.Product
import com.lhd.androidbase.data.repositories.FakeStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val productRepository: FakeStoreRepository) :
    BaseViewModel() {

    private var _lsProduct = MutableLiveData<List<Product>?>()
    val lsProduct: LiveData<List<Product>?>
        get() = _lsProduct


    override fun fetchData() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val lsProduct = productRepository.getAllProduct()
            _lsProduct.postValue(lsProduct)
        }
        registerJobFinish()
    }

}