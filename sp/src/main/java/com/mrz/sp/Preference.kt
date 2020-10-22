package com.mrz.sp

import android.content.Context
import android.content.ContextWrapper
import android.content.SharedPreferences
import com.mrz.sp.core.*
import com.mrz.sp.core.annotation.CorePreference

object Preference {
    val TAG = Preference.javaClass.name

    private lateinit var pref: SharedPreferences

    fun remove(key: String) {
        val prefs = getPreferences()
        val editor = prefs.edit()
        editor.remove(key)
        editor.apply()
    }

    private fun getPreferences(): SharedPreferences {
        return pref
    }

    operator fun contains(key: String): Boolean {
        return getPreferences().contains(key)
    }

    fun clear(): SharedPreferences.Editor {
        val editor = getPreferences().edit().clear()
        editor.apply()
        return editor
    }

    fun edit(): SharedPreferences.Editor {
        return getPreferences().edit()
    }


    operator fun get(key: String, type: Types): Any? {
        return get(key, type, type.defaultValue())
    }


    operator fun get(key: String?, type: Types, defaultValue: Any): Any? {
        val sp = getPreferences()
        return type.get(sp, key, defaultValue)
    }


    fun put(key: String, type: Types, value: Any) {
        type.put(getPreferences().edit(), key, value)

    }

    fun init(context: Context?) {
        context?.let {
            val annotation = context.javaClass.getAnnotation(CorePreference::class.java)
                ?: throw IllegalStateException("annotation not found")
            val prefsName = annotation.fileName
            val mode =
                if (annotation.contextWrapper == -1) ContextWrapper.MODE_PRIVATE else annotation.contextWrapper
            pref = context.getSharedPreferences(prefsName, mode)
        }
    }

    fun init(context: Context?, prefsName: String, mode: Int = -1) {
        context?.let {
            pref = context.getSharedPreferences(prefsName, mode)
        }
    }

}
