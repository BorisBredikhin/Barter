package ru.bredikhinpechnnikov.barter

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.bredikhinpechnnikov.barter.ui.AppActivity
import ru.bredikhinpechnnikov.barter.ui.login.LoginFragment

class MainActivity : AppCompatActivity() {
    private var userToken: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userToken = getPreferences(Context.MODE_PRIVATE).userToken
        if (userToken == null)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment, LoginFragment())
                .commit()
        else {
//          getPreferences(Context.MODE_PRIVATE).userToken
            userAlreadyHasToken(userToken!!)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        userAlreadyHasToken(userToken!!)
    }

    private fun userAlreadyHasToken(userToken: String) {
        val intent = Intent(applicationContext, AppActivity::class.java)
        intent.putExtra("token", userToken)
        startActivity(intent)
    }
}