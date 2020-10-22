package com.mrz.sp.test

import com.mrz.sp.Preference
import com.mrz.sp.core.Types

enum class SP  constructor(private val type: Types) {

    ENV(Types.StringType),
    IS_GOOD(Types.BooleanType),
    AGE(Types.IntegerType),
    FLOAT_VALUE(Types.FloatType),
    ID(Types.LongType);
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