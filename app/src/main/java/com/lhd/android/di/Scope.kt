package com.lhd.android.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class MainSite(
)

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class FatherOfApps()