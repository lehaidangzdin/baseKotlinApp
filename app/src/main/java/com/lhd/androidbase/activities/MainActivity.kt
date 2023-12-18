package com.lhd.androidbase.activities

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.lhd.androidbase.R
import com.lhd.androidbase.base.activities.BaseActivity
import com.lhd.androidbase.ui.example_service.JobSchedulerService
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private var loadingLayout: FrameLayout? = null

    companion object {
        private const val PERIODIC_TIME: Long = 15 * 60 * 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e("Frank", "MainActivity")
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_main)
        loadingLayout = findViewById(R.id.loadingLayout)
        //
        scheduleJob(this)
    }

    override fun showLoading(isShow: Boolean) {
        loadingLayout?.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    private fun scheduleJob(context: Context) {
        val componentName = ComponentName(context, JobSchedulerService::class.java)
        val jobInfoBuilder = JobInfo.Builder(JobSchedulerService.ID_JOB, componentName)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY) // Điều kiện mạng
//            .setPersisted(true)// Lưu trạng thái của công việc sau khi khởi động lại hệ thống
//            .setPeriodic(5000)
//            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
//            .setRequiresDeviceIdle(false)
//            .setRequiresCharging(true)
            .setPersisted(true)
            .setPeriodic(PERIODIC_TIME)

        // Cài đặt thêm các điều kiện và tùy chọn khác nếu cần.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            jobInfoBuilder.setRequiresBatteryNotLow(true) // Yêu cầu pin có thể sử dụng
        }

        val jobInfo = jobInfoBuilder.build()
        val jobScheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.schedule(jobInfo)
    }


}