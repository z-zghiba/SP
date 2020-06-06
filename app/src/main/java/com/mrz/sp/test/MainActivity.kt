package com.mrz.sp.test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    val TAG = "PREF==>"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SP.clearAll()

        Log.d(TAG, "ENV " + SP.ENV.get())
        SP.ENV.put("PROD")
        Log.d(TAG, "ENV " + SP.ENV.get())


        Log.d(TAG, "IS_GOOD " + SP.IS_GOOD.get())
        SP.IS_GOOD.put(true)
        Log.d(TAG, "IS_GOOD " + SP.IS_GOOD.get())

        Log.d(TAG, "AGE " + SP.AGE.get())
        SP.AGE.put(60)
        Log.d(TAG, "AGE " + SP.AGE.get())


        Log.d(TAG, "ID " + SP.ID.get())
        SP.ID.put(System.currentTimeMillis())
        Log.d(TAG, "ID " + SP.ID.get())


        Log.d(TAG, "FLOAT_VALUE " + SP.FLOAT_VALUE.get())
        SP.FLOAT_VALUE.put( -125.563f)
        Log.d(TAG, "FLOAT_VALUE " + SP.FLOAT_VALUE.get())


    }
}
