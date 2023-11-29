package com.lhd.androidbase.ui.fake_image

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.lhd.androidbase.base.fragment.BaseFragment
import com.lhd.androidbase.databinding.FragmentImageBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ImageFragment : BaseFragment<FragmentImageBinding>(FragmentImageBinding::inflate) {

    private val viewModel by viewModels<ImagesViewModel>()
    private val adapter = ImageAdapter(arrayListOf())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        registerAllExceptionEvent(viewModel, viewLifecycleOwner)
        registerObserverLoadingEvent(viewModel, viewLifecycleOwner)
        registerObserver()
    }

    private fun registerObserver() {
        viewModel.lsImage.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                adapter.setData(it)
                binding.rcv.adapter = adapter
            }
        }
    }

    companion object {
        const val TAG = "ImageFragment"
    }

}