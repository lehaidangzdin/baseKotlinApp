package com.lhd.androidbase.ui.home

import ProductAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.lhd.androidbase.R
import com.lhd.androidbase.base.fragment.BaseFragment
import com.lhd.androidbase.data.models.Product
import com.lhd.androidbase.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    ProductAdapter.OnClickItem {

    private val viewModel by viewModels<HomeViewModel>()
    private var adapter = ProductAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        registerAllExceptionEvent(viewModel, viewLifecycleOwner)
        registerObserverLoadingEvent(viewModel, viewLifecycleOwner)
        viewModel.lsProduct.observe(viewLifecycleOwner) { products ->
//            Log.d("TAGGGG", "onViewCreated: $products")
            if (products?.isNotEmpty() == true) {
                adapter.setData(products)
                adapter.setOnClickItem(this)
                binding.recyclerView.adapter = adapter
            }
        }
    }

    override fun clickItem(results: Product) {
        Log.e("HOME_FRAGMENT", "clickItem")
        val action = HomeFragmentDirections.actionHomeFragmentToSearchFragment()
        navigateToPage(action.actionId)
    }


}