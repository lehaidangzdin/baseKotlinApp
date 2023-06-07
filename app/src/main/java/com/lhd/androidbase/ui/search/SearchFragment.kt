package com.lhd.androidbase.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lhd.androidbase.base.fragment.BaseFragment
import com.lhd.androidbase.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment() {
    private lateinit var dataBinding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = FragmentSearchBinding.inflate(inflater)
        dataBinding.lifecycleOwner = viewLifecycleOwner

        dataBinding.txtBack.setOnClickListener {
            showErrorMessage("err")
        }

        return dataBinding.root
    }
}