package com.lhd.androidbase.ui.example_service

import android.Manifest
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import com.lhd.androidbase.base.fragment.BaseFragment
import com.lhd.androidbase.common.PermissionManager
import com.lhd.androidbase.common.Utils
import com.lhd.androidbase.databinding.FragmentExampleServiceBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExampleServiceFragment :
    BaseFragment<FragmentExampleServiceBinding>(FragmentExampleServiceBinding::inflate){

    private val viewModel by viewModels<ExampleServiceViewModel>()
    private var isGrantedPermission = false
    private lateinit var mLocationManager: LocationManager
    private val permissions = arrayOf(
        Manifest.permission.ACCESS_WIFI_STATE,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,

        )

    companion object {
        const val TAG: String = "LHD"
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        registerAllExceptionEvent(viewModel, viewLifecycleOwner)
        registerObserverLoadingEvent(viewModel, viewLifecycleOwner)
        registerObserver()
        //

        PermissionManager.requestPermissions(
            requireActivity(),
            arrayOf(
                Manifest.permission.POST_NOTIFICATIONS
            ),
            onPermissionsGranted = {
                Toast.makeText(context, "Permission granted", Toast.LENGTH_SHORT)
                    .show()
                isGrantedPermission = true
//                        viewModel.exampleWorker(requireContext(), viewLifecycleOwner)
//
//                        val action =
//                            ExampleServiceFragmentDirections.actionExampleServiceFragmentToSearchFragment()
//                        navigateToPage(action.actionId)
            },
            onPermissionsDenied = {
                Toast.makeText(context, "Need permission notification", Toast.LENGTH_SHORT)
                    .show()
            },
        )
        binding.btnDoWork.setOnClickListener {
            if (isGrantedPermission) {
                viewModel.exampleWorker(requireContext(), viewLifecycleOwner)
                val action =
                    ExampleServiceFragmentDirections.actionExampleServiceFragmentToSearchFragment()
                navigateToPage(action.actionId)

            }
        }


    }

    private fun registerObserver() {
        viewModel.progressWork.observe(viewLifecycleOwner) {
//            binding.tvProgress.text = it.ifEmpty { "loading..." }
            if (it.isNotEmpty())
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
        //
        PermissionManager.requestPermissions(
            requireActivity(),
            permissions,
            onPermissionsGranted = {
                isGrantedPermission = true
            },
            onPermissionsDenied = {
//                binding.idIp.text = "permission denied"
                Toast.makeText(context, "PERMISSION DENIED", Toast.LENGTH_SHORT).show()
            })

        binding.btn.setOnClickListener {
            if (!isGrantedPermission) {
                Toast.makeText(context, "permission denied", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // val ip = getIPAddress(true)
            // binding.idIp.text = ip
            if (!Utils.isLocationEnabled(requireContext())) {
                Toast.makeText(context, "must enable location", Toast.LENGTH_SHORT).show()
                Utils.goToSettingLocation(requireActivity())
            }

//            binding.idIp.text = "${getLastKnownLocation()!!.altitude}"
        }

    }

//    private fun getLastKnownLocation(): Location? {
//        mLocationManager =
//            activity!!.getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        val providers: List<String> = mLocationManager.getProviders(true)
//        var bestLocation: Location? = null
//        for (provider in providers) {
//            val l: Location =
//                mLocationManager.getLastKnownLocation(provider) ?: continue
//            if (bestLocation == null
//                || l.accuracy < bestLocation.accuracy
//            ) {
//                bestLocation = l
//            }
//        }
//        return bestLocation
//    }
}