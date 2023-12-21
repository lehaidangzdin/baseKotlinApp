package com.lhd.androidbase.ui.fake_image

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.lhd.androidbase.base.fragment.BaseFragment
import com.lhd.androidbase.common.Utils
import com.lhd.androidbase.data.entity.params_entity.RegisterEntity
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
//        viewModel.lsImage.observe(viewLifecycleOwner) {
//            if (it.isNotEmpty()) {
//                adapter.setData(it)
//                binding.rcv.adapter = adapter
//            }
//        }
        //
        binding.btnRegister.setOnClickListener {
            val username = binding.edUsername.text
            val email = binding.edEmail.text
            val password = binding.edPassword.text
            val params = RegisterEntity(
                username.trim().toString(),
                email.trim().toString(),
                password.trim().toString()
            )
            viewModel.register(params)
            // after click btn
            Utils.hideSoftKeyboard(view!!, requireContext())
            username.clear()
            email.clear()
            password.clear()
        }
        //
        viewModel.lvdRegister.observe(viewLifecycleOwner) {
            if (it != null) {
                Toast.makeText(requireContext(), "Register success!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        const val TAG = "ImageFragment"
    }

}