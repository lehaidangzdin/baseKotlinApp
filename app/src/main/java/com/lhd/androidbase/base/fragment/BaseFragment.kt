package com.lhd.androidbase.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.lhd.androidbase.R
import com.lhd.androidbase.base.activities.BaseActivity
import com.lhd.androidbase.base.network.BaseNetworkException
import com.lhd.androidbase.base.viewmodel.BaseViewModel
import com.lhd.androidbase.common.EventObserver

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding>(
    private val inflate: Inflate<VB>
) : Fragment() {

    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected fun navigateToPage(actionId: Int) {
        findNavController().navigate(actionId)
    }

    protected fun backStack() {
        findNavController().popBackStack()
    }

    protected fun showLoading(isShow: Boolean) {
        val activity = requireActivity()
        if (activity is BaseActivity) {
            activity.showLoading(isShow)
        }
    }

    protected fun showErrorMessage(e: BaseNetworkException) {
        showErrorMessage(e.mainMessage)
    }

    protected fun showErrorMessage(messageId: Int) {
        val message = requireContext().getString(messageId)
        showErrorMessage(message)
    }

    protected fun showErrorMessage(message: String) {
        val activity = requireActivity()
        if (activity is BaseActivity) {
            activity.showErrorDialog(message)
        }
    }

    protected fun showNotify(title: String?, message: String) {
        val activity = requireActivity()
        if (activity is BaseActivity) {
            activity.showNotifyDialog(message, title ?: getDefaultNotifyTitle())
        }
    }

    protected fun showNotify(titleId: Int = R.string.default_notify_title, messageId: Int) {
        val activity = requireActivity()
        if (activity is BaseActivity) {
            activity.showNotifyDialog(titleId, messageId)
        }
    }

    protected fun registerObserverExceptionEvent(
        viewModel: BaseViewModel,
        viewLifecycleOwner: LifecycleOwner
    ) {
        viewModel.baseNetworkException.observe(viewLifecycleOwner, EventObserver {
            showErrorMessage(it)
        })
    }

    protected fun registerObserverNetworkExceptionEvent(
        viewModel: BaseViewModel,
        viewLifecycleOwner: LifecycleOwner
    ) {
        viewModel.networkException.observe(viewLifecycleOwner, EventObserver {
            showNotify(getDefaultNotifyTitle(), it.responseMessage ?: "Network error")
        })
    }

    protected fun registerObserverMessageEvent(
        viewModel: BaseViewModel,
        viewLifecycleOwner: LifecycleOwner
    ) {
        viewModel.errorMessageResourceId.observe(viewLifecycleOwner, EventObserver { message ->
            showErrorMessage(message)
        })
    }

    protected fun registerObserverLoadingMoreEvent(
        viewModel: BaseViewModel,
        viewLifecycleOwner: LifecycleOwner
    ) {
        viewModel.isLoadingMore.observe(viewLifecycleOwner, EventObserver { isShow ->
            showLoadingMore(isShow)
        })
    }

    protected fun showLoadingMore(isShow: Boolean) {

    }


    private fun getDefaultNotifyTitle(): String {
        return getString(R.string.default_notify_title)
    }

    protected fun registerAllExceptionEvent(
        viewModel: BaseViewModel,
        viewLifecycleOwner: LifecycleOwner
    ) {
        registerObserverExceptionEvent(viewModel, viewLifecycleOwner)
        registerObserverNetworkExceptionEvent(viewModel, viewLifecycleOwner)
        registerObserverMessageEvent(viewModel, viewLifecycleOwner)
    }

    protected fun registerObserverLoadingEvent(
        viewModel: BaseViewModel,
        viewLifecycleOwner: LifecycleOwner
    ) {
        viewModel.isLoading.observe(viewLifecycleOwner, EventObserver { isShow ->
            showLoading(isShow)
        })
    }


}