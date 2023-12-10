package com.lhd.androidbase.ui.search

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import com.lhd.androidbase.base.fragment.BaseFragment
import com.lhd.androidbase.common.PermissionManager
import com.lhd.androidbase.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {


    private val permissions = arrayOf(
        Manifest.permission.READ_CONTACTS,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.CAMERA
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner


//        binding.bg.setOnTouchListener { _, e ->
//            // toạ độ khi touch
//            Log.e("TAGGGGGGGG", "onViewCreated: x=${e.x} || y=${e.y}")
//            Log.e("TAGGGGGGGG", "onViewCreated: y=${binding.btn.height}")
//            // y = y khi touch - chiều rộng của btn khi draw circle mới chính xác
//            binding.cCir.drawCircle(e.x, e.y - binding.btn.height)
//            false
//        }
//        activity?.let {
//            PermissionManager.requestPermissions(
//                it, permissions,
//                {
//                    Log.e(TAG, "onViewCreated: permission granted")
//                },
//                {
//                    Log.e(TAG, "onViewCreated: permission denied")
//                },
//            )
//        }

        PermissionManager.requestPermissions(
            requireActivity(), permissions,
            {
                Log.e(TAG, "onViewCreated: permission granted")
            },
            {
                showConfirmDialog(
                    "TITLE", "permission denied",
                    posCallback = {
                        Log.e(TAG, "onViewCreated: permission denied")
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        val uri = Uri.fromParts("package", context?.packageName, null)
                        intent.data = uri
                        startActivity(intent)
                    },
                    // TODO: Đang lỗi
                    nevCallback = {
                        Log.e(TAG, "onViewCreated: neg callback")
                    },
                )
            },
        )

    }

    companion object {
        const val TAG = "Dqwdqwdqw"
    }

}