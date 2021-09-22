# SP  (This project is no longer supported)
Simple way to manage SharedPreferences for Android whith kotlin

# Usage
Init Preference


    @CorePreference(fileName = "SP_Z")
    open class App  : Application(){

        override fun onCreate() {
            super.onCreate()
            Preference.init(this)
        }
    }



Create an enum class which contains the attributes to manage  like this :


    class SPService {
        var IS_GOOD_USER: Boolean by Preference.boolean("IS_GOOD_USER", true)
        var ENV: String? by Preference.string("ENV", "DEV")
        var AGE: Int by Preference.int("AGE",18)
        var PRICE : Float by Preference.float("PRICE")
        var USER_ID : Long by Preference.long("USER_ID")
        var USERS_SET  : Set<String>? by Preference.stringSet("USER_LIST")
    }
    
    

