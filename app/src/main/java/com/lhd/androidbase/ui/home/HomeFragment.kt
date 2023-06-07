package com.lhd.androidbase.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.lhd.androidbase.R
import com.lhd.androidbase.base.fragment.BaseFragment
import com.lhd.androidbase.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment() {


    private lateinit var dataBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = FragmentHomeBinding.inflate(inflater)
        dataBinding.lifecycleOwner = viewLifecycleOwner

        dataBinding.txt.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_searchFragment) }


        return dataBinding.root
    }


}