package h.example.loginapp.MVP.Model

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import h.example.loginapp.MVP.ext.DeviceIdUtils
import h.example.loginapp.MVP.ext.LoginContract

class ModelMainActivity(private val context : Context) {

    fun getApi() = DeviceIdUtils.androidId(context)


}