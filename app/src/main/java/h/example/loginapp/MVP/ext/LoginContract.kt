package h.example.loginapp.MVP.ext

interface LoginContract {
    interface View {
        fun showEmail(email: String)
        fun showCode(code: String)
        fun showLoginSuccess()
        fun showLoginError()
    }

    interface Presenter {
        fun loadUserData()
        fun saveUserData(email: String, code: String)
        fun login(email: String, code: String)
    }

    interface Model {
        fun getEmail(): String
        fun getCode(): String
        fun setEmail(email: String)
        fun setCode(code: String)
    }
}
