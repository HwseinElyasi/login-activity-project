package h.example.loginapp.remote.extRemote

import com.google.gson.Gson
import h.example.loginapp.remote.Model.ErrorModel
import retrofit2.Response

object ErrorUtils {

    fun errorVerify(response : Response<*>): String {
        var error : String? = null
        val errorBode = response.errorBody()

        if (errorBode != null)
            error = Gson().fromJson(errorBode.string() , ErrorModel::class.java).message

        return error ?: "ارتباط با سرور امکان پذیر نبود"


    }

}