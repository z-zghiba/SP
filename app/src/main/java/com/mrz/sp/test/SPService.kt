package com.mrz.sp.test

import android.content.SharedPreferences
import com.mrz.sp.Preference

class SPService {
    var IS_GOOD_USER: Boolean by Preference.boolean("IS_GOOD_USER", true)
    var ENV: String? by Preference.string("ENV", "DEV")
    var AGE: Int by Preference.int("AGE",18)
    var PRICE : Float by Preference.float("PRICE")
    var USER_ID : Long by Preference.long("USER_ID")
    var USERS_SET  : Set<String>? by Preference.stringSet("USER_LIST")


}