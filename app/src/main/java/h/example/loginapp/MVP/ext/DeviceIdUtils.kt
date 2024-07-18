package h.example.loginapp.MVP.ext

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings

object DeviceIdUtils {

   private var androidId : String? = null

    @SuppressLint("HardwareIds")
    fun androidId(context: Context) : String {

        if (androidId == null)
            androidId = Settings.Secure.getString(
                context.contentResolver ,
                Settings.Secure.ANDROID_ID
            )

        return androidId ?: ""

    }


}