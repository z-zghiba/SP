package com.mrz.sp.test

import android.content.SharedPreferences
import android.os.Build
import com.mrz.sp.core.annotation.CorePreference
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.P,Build.VERSION_CODES.P],application = App::class)
@RunWith(RobolectricTestRunner::class)
internal class AppTest {

    private lateinit var  app :App
    private lateinit var   spService :SPService
    private lateinit var  sp :SharedPreferences

    @Before
   fun setup(){
       app = App()
      /* sp = ApplicationProvider.getApplicationContext<App>().getSharedPreferences(
            "you_custom_pref_name", Context.MODE_PRIVATE);*/
          spService = SPService()
    }

    @Test
    fun annotation_test_check() {
        val annotation = app.javaClass.getAnnotation(CorePreference::class.java)
        Assert.assertNotNull(annotation)
    }

    @Test
    fun string_test_check() {
         Assert.assertNotNull(spService.ENV)
        spService.ENV = "PROD"
        Assert.assertEquals("PROD",spService.ENV)
    }

    @Test
    fun boolean_test_check() {
         Assert.assertTrue(spService.IS_GOOD_USER)
        spService.IS_GOOD_USER = false
        Assert.assertFalse(spService.IS_GOOD_USER)
    }

    @Test
    fun int_test_check() {
         Assert.assertEquals(18,spService.AGE)
        spService.AGE = 60
        Assert.assertEquals(60,spService.AGE)

    }

    @Test
    fun long_test_check() {
         Assert.assertNotNull(spService.USER_ID)
        val currentValue  =  System.currentTimeMillis()
        spService.USER_ID = currentValue
        Assert.assertEquals(currentValue,spService.USER_ID)
    }

    @Test
    fun float_test_check() {
        Assert.assertNotNull(spService.PRICE)
        spService.PRICE = 13.129f
        Assert.assertEquals(13.129f,spService.PRICE)
    }


    @Test
    fun stringSet_test_check() {
         Assert.assertNotNull(spService.USERS_SET)
        spService.USERS_SET = setOf("zouhair","MrZ","ZZ")
        Assert.assertEquals(setOf("zouhair","MrZ","ZZ"),spService.USERS_SET)
    }
}