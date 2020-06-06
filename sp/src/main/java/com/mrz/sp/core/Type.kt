package com.mrz.sp.core


enum class Type(
    val type: Class<*>,
    val defaultValue: Any?,
    val methodGet: String,
    val methodPut: String
) {
   STRING(String::class.java, "", "getString", "putString"),
   //STRING_SET(Set::class.java,  null, "getStringSet", "putStringSet"),
   INTEGER(Int::class.java, 0, "getInt", "putInt"),
   BOOLEAN(Boolean::class.java, false, "getBoolean", "putBoolean"),
   FLOAT(Float::class.java, 0.0f, "getFloat", "putFloat"),
   LONG(Long::class.java, 0L, "getLong", "putLong");

   companion object {
        fun getMethodPut(classType: Type): String? {
           for (type in Type.values()) {
               if (type == classType) {
                   return type.methodPut
               }
           }
           return null
       }
   }
}
