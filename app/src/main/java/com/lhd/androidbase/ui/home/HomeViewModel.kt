package com.lhd.androidbase.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lhd.androidbase.base.viewmodel.BaseViewModel
import com.lhd.androidbase.data.models.Product
import com.lhd.androidbase.data.repositories.FakeStoreRepository
import com.lhd.androidbase.data.repositories.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: FakeStoreRepository,
    private val firebaseRepository: FirebaseRepository
) :
    BaseViewModel() {

    private var _lsProduct = MutableLiveData<List<Product>?>()
    val lsProduct: LiveData<List<Product>?>
        get() = _lsProduct

//    private var _lsProduct2 = MutableLiveData<List<Product>?>()
//    val lsProduct2: LiveData<List<Product>?>
//        get() = _lsProduct2


    override fun fetchData() {
        showLoading(true)
        parentJob = viewModelScope.launch(handler) {
            val lsProduct = productRepository.getAllProduct()
            _lsProduct.postValue(lsProduct)
//
//            val ls = firebaseRepository.getAll();
//            _lsProduct2.postValue(ls);
//
//            val product = Product(7, "food", "food color green", "image", 1000.0, "good food")
//            val result = firebaseRepository.addUser(product)
//            Log.d("TAGGGG", "fetchData: $result")
        }
        registerJobFinish()
    }

}