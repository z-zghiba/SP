package com.mrz.sp.test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.mrz.sp.Preference
import java.util.*

class MainActivity : AppCompatActivity() {

    
    private val TAG = "PREF==>"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Preference.clear()



    }
}
