package ru.bredikhinpechnnikov.barter.ui

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import ru.bredikhinpechnnikov.barter.R
import ru.bredikhinpechnnikov.barter.data.model.Profile
import ru.bredikhinpechnnikov.barter.net.getUserData
import ru.bredikhinpechnnikov.barter.userToken

class AppActivity : AppCompatActivity() {
    private lateinit var userdata: Profile
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)

        Log.d("barter", intent.getStringExtra("token") ?: "")
   }

    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()

        Log.d("barter", intent.getStringExtra("token") ?: "")

        val token = intent.getStringExtra("token")
        userdata = getUserData(token!!)
        findViewById<TextView>(R.id.username).text = "${userdata.firstName} ${userdata.lastName}"

    }
}