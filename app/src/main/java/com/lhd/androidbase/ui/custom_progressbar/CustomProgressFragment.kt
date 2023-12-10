package com.lhd.androidbase.ui.custom_progressbar

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import androidx.core.view.ContentInfoCompat.Flags
import com.lhd.androidbase.base.fragment.BaseFragment
import com.lhd.androidbase.common.PermissionManager
import com.lhd.androidbase.databinding.FragmentCustomProgressBinding
import com.lhd.androidbase.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CustomProgressFragment :
    BaseFragment<FragmentCustomProgressBinding>(FragmentCustomProgressBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.cusProgress.lsMileStones = arrayOf(0.2f, 0.5f, 0.8f)
        //
        setUpObserver()
    }

    private fun setUpObserver() {

    }

    companion object {
        const val TAG = "CustomProgressFragment"
    }

}