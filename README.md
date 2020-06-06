# SP
Simple way to manage SharedPreferences for Android whith kotlin

# Usage
Create an enum class which contains the attributes to manage  like this :


    enum class SP  constructor(private val type: Type) {
        //create attributes with their type
        ENV(Type.STRING),
        IS_GOOD(Type.BOOLEAN),
        AGE(Type.INTEGER),
        FLOAT_VALUE(Type.FLOAT),
        ID(Type.LONG);
        
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
    
    

