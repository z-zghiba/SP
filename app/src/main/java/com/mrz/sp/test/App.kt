package com.mrz.sp.test

import android.app.Application
import com.mrz.sp.Preference
import com.mrz.sp.core.annotation.CorePreference


@CorePreference(fileName = "SP_Z")
open class App  : Application(){
    override fun onCreate() {
        super.onCreate()
        Preference.init(this)
    }
}