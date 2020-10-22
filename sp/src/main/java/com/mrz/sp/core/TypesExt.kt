package com.mrz.sp.core

import android.content.SharedPreferences
import android.util.Log
import com.mrz.sp.Preference
import java.lang.reflect.InvocationTargetException



fun Types.classType(): Class<*>  = when (this) {
    is Types.StringType -> String::class.java
    is Types.IntegerType -> Int::class.java
    is Types.BooleanType -> Boolean::class.java
    is Types.FloatType -> Float::class.java
    is Types.LongType -> Long::class.java
}


private fun Types.getMethodName(): String = when (this) {
    is Types.StringType -> "getString"
    is Types.IntegerType -> "getInt"
    is Types.BooleanType -> "getBoolean"
    is Types.FloatType -> "getFloat"
    is Types.LongType -> "getLong"
}

fun Types.putMethodName(): String = when (this) {
    is Types.StringType -> "putString"
    is Types.IntegerType -> "putInt"
    is Types.BooleanType -> "putBoolean"
    is Types.FloatType -> "putFloat"
    is Types.LongType -> "putLong"
}

fun Types.defaultValue(): Any = when (this) {
    is Types.StringType -> ""
    is Types.IntegerType -> 0
    is Types.BooleanType -> false
    is Types.FloatType ->  0.0f
    is Types.LongType -> 0L
}

fun Types.get(
    sp: SharedPreferences,
    key: String?,
    defaultValue: Any
): Any? {
    try {
        val method = sp.javaClass.getMethod(this.getMethodName(), String::class.java, this.classType())
        return method.invoke(sp, key, defaultValue)
    } catch (e: NoSuchMethodException) {
        Log.e(Preference.TAG, "method not found", e)
    } catch (e: IllegalAccessException) {
        Log.e(Preference.TAG, "cant access", e)
    } catch (e: InvocationTargetException) {
        Log.e(Preference.TAG, "cant invoke method", e)
    }
    return this.defaultValue()
}

fun Types.put(
    editor: SharedPreferences.Editor,
    key: String?,
    value: Any
) {
    try {
        val method =  editor.javaClass.getMethod(this.getMethodName(), String::class.java, this.classType())
        method.invoke(editor, key, value)
        editor.apply()
    } catch (e: NoSuchMethodException) {
        Log.e(Preference.TAG, "method not found", e)
    } catch (e: IllegalAccessException) {
        Log.e(Preference.TAG,  "cant access", e)
    } catch (e: InvocationTargetException) {
        Log.e(Preference.TAG,  "cant invoke method", e)
    }
}



