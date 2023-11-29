package com.lhd.androidbase.ui.fake_image

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lhd.androidbase.base.viewmodel.BaseViewModel
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


    init {
        fetchData()
    }

    override fun fetchData() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val image = imageRepository.getAllImage()
            _lsImage.postValue(image.data)
        }
        registerJobFinish()
    }


    companion object {
        const val TAG = "ImagesViewModel"
    }

}