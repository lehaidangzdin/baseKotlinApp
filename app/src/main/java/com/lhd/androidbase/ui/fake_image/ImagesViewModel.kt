package com.lhd.androidbase.ui.fake_image

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lhd.androidbase.base.viewmodel.BaseViewModel
import com.lhd.androidbase.data.entity.params_entity.RegisterEntity
import com.lhd.androidbase.data.entity.reponse_entity.RegisterResponseEntity
import com.lhd.androidbase.data.repositories.FakeImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImagesViewModel @Inject constructor(
    private val imageRepository: FakeImageRepository,
) :
    BaseViewModel() {
    private var _lsImage = MutableLiveData<List<String>>()
    val lsImage: LiveData<List<String>>
        get() = _lsImage

    private var _registerResponse = MutableLiveData<RegisterResponseEntity>()
    val registerResponse: LiveData<RegisterResponseEntity>
        get() = _registerResponse

    companion object {
        const val TAG = "ImagesViewModel"
    }

    init {
        fetchData()
    }


    override fun fetchData() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val image = imageRepository.getAllImage()
//            Log.e(TAG, "fetchData: ${image.data}")
            _lsImage.value = (image.data as List<String>?)!!
//            Logger.log(TAG, "$lsImage")
        }
        registerJobFinish()
    }

    fun register(registerModel: RegisterEntity) {
        // TODO: LỖI CONVERT MODEL
        showLoading(true)
        viewModelScope.launch {
            val register = imageRepository.register(registerEntity = registerModel)
            _registerResponse.postValue(register.data as RegisterResponseEntity)
            Log.e(TAG, "register: $register")
        }
        registerJobFinish()
    }

}