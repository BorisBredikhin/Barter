package ru.bredikhinpechnnikov.barter

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AlertDialog
import ru.bredikhinpechnnikov.barter.ui.AppActivity
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
//          getPreferences(Context.MODE_PRIVATE).userToken
            userAlreadyHasToken()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        userAlreadyHasToken()
    }

    private fun userAlreadyHasToken(){
        startActivity(Intent(applicationContext, AppActivity::class.java))
    }
}