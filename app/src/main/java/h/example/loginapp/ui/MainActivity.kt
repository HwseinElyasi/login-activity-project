package h.example.loginapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import h.example.loginapp.MVP.Model.ModelMainActivity
import h.example.loginapp.MVP.Presenter.PresenterMainActivity
import h.example.loginapp.MVP.View.ViewMainActivity
import h.example.loginapp.MVP.ext.InfoActivity

class MainActivity : AppCompatActivity() , InfoActivity {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ViewMainActivity(this ,this)
        setContentView(view.binding.root)

        val presenter = PresenterMainActivity(view , ModelMainActivity(this))
        presenter.onCreate()
    }

    override fun finished() {
        finish()
    }


    }
