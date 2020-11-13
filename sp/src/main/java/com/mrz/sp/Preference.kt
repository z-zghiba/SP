package com.mrz.sp

import android.content.Context
import android.content.ContextWrapper
import android.content.SharedPreferences
import com.mrz.sp.core.*
import com.mrz.sp.core.annotation.CorePreference
import kotlin.properties.ReadWriteProperty

object Preference {

    private lateinit var pref: SharedPreferences

    fun remove(key: String) {
        val editor = pref.edit()
        editor.remove(key)
        editor.apply()
    }


    operator fun contains(key: String): Boolean {
        return pref.contains(key)
    }

    fun clear(): SharedPreferences.Editor {
        val editor = pref.edit().clear()
        editor.apply()
        return editor
    }


    fun init(context: Context?) {
        context?.let {
            val annotation = context.javaClass.getAnnotation(CorePreference::class.java)
                ?: throw IllegalStateException("annotation not found")
            val prefsName = annotation.fileName
            val mode =
                if (annotation.contextWrapper == -1) ContextWrapper.MODE_PRIVATE else annotation.contextWrapper
            init(context, prefsName, mode)
        }
    }

    fun init(context: Context?, prefsName: String, mode: Int = -1) {
        context?.let {
            pref = context.getSharedPreferences(prefsName, mode)
        }
    }


    fun int(
        key: String? = null,
        defaultValue: Int = 0
    ): ReadWriteProperty<Any, Int> {
        return pref.delegate(
            key,
            SharedPreferences::getInt,
            SharedPreferences.Editor::putInt,
            defaultValue
        )
    }

    fun long(
        key: String? = null,
        defaultValue: Long = 0
    ): ReadWriteProperty<Any, Long> {
        return pref.delegate(
            key,
            SharedPreferences::getLong,
            SharedPreferences.Editor::putLong,
            defaultValue
        )
    }

    fun float(
        key: String? = null,
        defaultValue: Float = 0f
    ): ReadWriteProperty<Any, Float> {
        return pref.delegate(
            key,
            SharedPreferences::getFloat,
            SharedPreferences.Editor::putFloat,
            defaultValue
        )
    }

    fun boolean(
        key: String? = null,
        defaultValue: Boolean = false
    ): ReadWriteProperty<Any, Boolean> {
        return pref.delegate(
            key,
            SharedPreferences::getBoolean,
            SharedPreferences.Editor::putBoolean,
            defaultValue
        )
    }

    fun stringSet(
        key: String? = null,
        defaultValue: Set<String> = emptySet()
    ): ReadWriteProperty<Any, Set<String>?> {
        return pref.delegate(
            key,
            SharedPreferences::getStringSet,
            SharedPreferences.Editor::putStringSet,
            defaultValue
        )
    }

    fun string(
        key: String? = null,
        defaultValue: String = ""
    ): ReadWriteProperty<Any, String?> {
        return pref.delegate(
            key,
            SharedPreferences::getString,
            SharedPreferences.Editor::putString,
            defaultValue
        )
    }

}
