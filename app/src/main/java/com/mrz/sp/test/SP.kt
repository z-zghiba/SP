package com.mrz.sp.test

import com.mrz.sp.Preference
import com.mrz.sp.core.Type

enum class SP  constructor(private val type: Type) {

    ENV(Type.STRING),
    IS_GOOD(Type.BOOLEAN),
    AGE(Type.INTEGER),
    FLOAT_VALUE(Type.FLOAT),
    ID(Type.LONG);
   // MY_PHONES(Type.STRING_SET);






    fun put(value: Any) {
        Preference.put(this.name, type, value)
    }

    fun  get(): Any? {
        return Preference[this.name, type]
    }

    companion object{
        fun clearAll (){
            Preference.clear()
        }
    }
}