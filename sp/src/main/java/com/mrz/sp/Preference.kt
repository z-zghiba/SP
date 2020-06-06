package com.mrz.sp

import android.content.Context
import android.content.ContextWrapper
import android.content.SharedPreferences
import android.util.Log
import com.mrz.sp.core.Type
import com.mrz.sp.core.annotation.CorePreference
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

public object Preference {
    val TAG =  Preference.javaClass.name

    private lateinit var pref : SharedPreferences

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


    operator fun  get(key: String, type: Type): Any? {
       return type.defaultValue?.let { get(key,type, type.defaultValue) }
    }


    operator fun   get(key: String?, type: Type, defaultValue: Any):  Any? {
        val sp = getPreferences()
        try {
            val method = sp.javaClass.getMethod(type.methodGet,String::class.java, type.type)
            return method.invoke(sp, key, defaultValue)
        } catch (e: NoSuchMethodException) {
            Log.e(TAG, "method not found", e)
         } catch (e: IllegalAccessException) {
            Log.e(TAG,  "cant access", e)
         } catch (e: InvocationTargetException) {
            Log.e(TAG,  "cant invoke method", e)
         }
        return type.defaultValue
    }



    fun put(key: String, type: Type, value: Any) {
        val editor = getPreferences().edit()
        try {
            val method = Type.getMethodPut(type)?.let { editor.javaClass.getMethod(it, String::class.java, type.type) }
            method!!.invoke(editor, key, value)
            editor.apply()
        } catch (e: NoSuchMethodException) {
            Log.e(TAG, "method not found", e)
        } catch (e: IllegalAccessException) {
            Log.e(TAG,  "cant access", e)
        } catch (e: InvocationTargetException) {
            Log.e(TAG,  "cant invoke method", e)
        }

    }

    fun init(context: Context?) {
        context?.let {
            val annotation = context.javaClass.getAnnotation(CorePreference::class.java) ?: throw IllegalStateException("annotation not found")
            val prefsName = annotation.fileName
            val mode = if (annotation.contextWrapper == -1) ContextWrapper.MODE_PRIVATE else annotation.contextWrapper
            pref = context.getSharedPreferences(prefsName, mode)
        }
    }

    fun init(context: Context?, prefsName : String, mode: Int = -1) {
        context?.let {
             pref = context.getSharedPreferences(prefsName, mode)
        }
    }

}
