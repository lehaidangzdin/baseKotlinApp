package com.lhd.androidbase.ui.example_service

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.lhd.androidbase.base.fragment.BaseFragment
import com.lhd.androidbase.databinding.FragmentExampleServiceBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ExampleServiceFragment :
    BaseFragment<FragmentExampleServiceBinding>(FragmentExampleServiceBinding::inflate) {

    private val viewModel by viewModels<ExampleServiceViewModel>()


    companion object {
        const val TAG: String = "ExampleServiceFragment"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        registerAllExceptionEvent(viewModel, viewLifecycleOwner)
        registerObserverLoadingEvent(viewModel, viewLifecycleOwner)
    }


}