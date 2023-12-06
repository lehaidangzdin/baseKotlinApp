package com.lhd.androidbase.ui.search

import android.os.Bundle
import android.util.Log
import android.view.View
import com.lhd.androidbase.base.fragment.BaseFragment
import com.lhd.androidbase.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner


        binding.bg.setOnTouchListener { _, e ->
            // toạ độ khi touch
            Log.e("TAGGGGGGGG", "onViewCreated: x=${e.x} || y=${e.y}")
            Log.e("TAGGGGGGGG", "onViewCreated: y=${binding.btn.height}")
            // y = y khi touch - chiều rộng của btn khi draw circle mới chính xác
            binding.cCir.drawCircle(e.x, e.y - binding.btn.height)
            false
        }


    }

}