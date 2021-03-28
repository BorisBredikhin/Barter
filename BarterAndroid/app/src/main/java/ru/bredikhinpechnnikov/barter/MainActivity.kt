package ru.bredikhinpechnnikov.barter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AlertDialog
import ru.bredikhinpechnnikov.barter.ui.login.LoginFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (getPreferences(Context.MODE_PRIVATE).userToken == null)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment, LoginFragment())
                .commit()
        else {
            // todo: open user page
            val builder = AlertDialog.Builder(this)
            builder.setMessage(getPreferences(Context.MODE_PRIVATE).userToken)

            builder.create().show()
        }
    }
}