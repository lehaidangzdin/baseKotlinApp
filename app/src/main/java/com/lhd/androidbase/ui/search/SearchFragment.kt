package com.lhd.androidbase.ui.search

import android.os.Bundle
import android.view.View
import com.lhd.androidbase.base.fragment.BaseFragment
import com.lhd.androidbase.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.txtBack.setOnClickListener {
            backStack()
        }
    }

}