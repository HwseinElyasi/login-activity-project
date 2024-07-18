package h.example.loginapp.MVP.Presenter

import h.example.loginapp.MVP.Model.ModelMainActivity
import h.example.loginapp.MVP.View.ViewMainActivity
import h.example.loginapp.MVP.ext.LoginContract

class PresenterMainActivity(
    private val view : ViewMainActivity ,
    private val model : ModelMainActivity
) {

    fun onCreate(){
        view.onClickHandler(model.getApi())
    }


}