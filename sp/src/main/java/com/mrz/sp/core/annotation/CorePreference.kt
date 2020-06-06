package com.mrz.sp.core.annotation


@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE)
annotation class CorePreference(val fileName: String, val contextWrapper: Int = -1)
