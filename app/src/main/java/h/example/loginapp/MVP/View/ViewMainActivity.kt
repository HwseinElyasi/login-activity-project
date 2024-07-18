package h.example.loginapp.MVP.View

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.view.Display.Mode
import android.view.LayoutInflater
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Toast
import h.example.loginapp.MVP.ext.InfoActivity
import h.example.loginapp.database.DBHandler
import h.example.loginapp.database.Model.UserEntity
import h.example.loginapp.databinding.ActivityMainBinding
import h.example.loginapp.remote.Model.ApiModel
import h.example.loginapp.remote.Model.DefaultModel
import h.example.loginapp.remote.RetrofitService
import h.example.loginapp.remote.extRemote.ErrorUtils
import h.example.loginapp.ui.MainActivity
import h.example.loginapp.ui.MainActivity2
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewMainActivity(
    private val context: Context,
    private val utils: InfoActivity
) {


    private lateinit var pref: SharedPreferences


    val binding =
        ActivityMainBinding.inflate(LayoutInflater.from(context))

    private var email = binding.edtInputEmail.text.toString()
    private var code = binding.edtCode.text.toString()

    @SuppressLint("SetTextI18n")
    fun onClickHandler(androidId: String) {

        binding.btnSend.setOnClickListener {

            val email = binding.edtInputEmail.text.toString()
            if (email.isEmpty()) {
                binding.textInputEmail.error = "Email Empty"
                return@setOnClickListener
            } else
                binding.textInputEmail.error = null

            sendCodeInEmail(email)

            binding.edtInputEmail.visibility = INVISIBLE
            binding.textInputEmail.visibility = INVISIBLE
            binding.btnSend.visibility = INVISIBLE

            binding.textInputCode.visibility = VISIBLE
            binding.edtCode.visibility = VISIBLE
            binding.txtWrong.visibility = VISIBLE
            binding.btnConfirm.visibility = VISIBLE
            binding.txtSendEmail.visibility = VISIBLE
            binding.txtSendEmail.text = "Send Code In : $email"

        }

        binding.txtWrong.setOnClickListener {

            binding.edtCode.visibility = INVISIBLE
            binding.textInputCode.visibility = INVISIBLE
            binding.txtWrong.visibility = INVISIBLE
            binding.btnConfirm.visibility = INVISIBLE
            binding.txtSendEmail.visibility = INVISIBLE

            binding.edtInputEmail.visibility = VISIBLE
            binding.textInputEmail.visibility = VISIBLE
            binding.btnSend.visibility = VISIBLE


        }

        binding.btnConfirm.setOnClickListener {

            email = binding.edtInputEmail.text.toString()
            code = binding.edtCode.text.toString()

            if ((code.isEmpty()) || (code.length < 6)) {
                binding.textInputCode.error = "Error"
                return@setOnClickListener

            } else

                pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE)

            val editor = pref.edit()
            editor.putBoolean("state", true)
            editor.putString("email", email)
            editor.putString("code", code)
            editor.apply()

                binding.textInputCode.error = null

            confirmCode(androidId, code, email)

        }

       val share = context.getSharedPreferences("pref" , Context.MODE_PRIVATE)
       val login = share.getBoolean("state" , false)

        if (login) {
            context.startActivity(Intent(context, MainActivity2::class.java))
            utils.finished()
        }
    }


    private fun confirmCode(androidId: String, code: String, email: String) {

        val service = RetrofitService.apiService

        CoroutineScope(Dispatchers.IO).launch {

            try {
                val response = service.verifyCode(androidId, code, email)

                if (response.isSuccessful)
                    launch(Dispatchers.Main) {

                        val data = response.body() as ApiModel
                        showText("ورود شما موفقیت آمیز بود")
                        context.startActivity(Intent(context, MainActivity2::class.java))
                        utils.finished()
                        data.api
                    } else {
                    launch(Dispatchers.Main) {
                        showText(ErrorUtils.errorVerify(response))
                    }
                }
            } catch (e: Exception) {
                Log.i("ERROR_CONFIRM_BTN", e.message.toString())
            }


        }

    }


    private fun sendCodeInEmail(email: String) {

        val service = RetrofitService.apiService

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = service.sendCode(email)

                if (response.isSuccessful)
                    launch(Dispatchers.Main) {
                        val data = response.body() as DefaultModel
                        showText(data.message)
                    } else {
                    launch(Dispatchers.Main) {
                        showText(ErrorUtils.errorVerify(response))
                    }
                }
            } catch (e: Exception) {
                Log.i("ERROR_CODING", e.message.toString())
            }
        }


    }

    private fun showText(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }



}