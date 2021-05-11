package ru.bredikhinpechnnikov.barter.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.bredikhinpechnnikov.barter.R
import ru.bredikhinpechnnikov.barter.data.model.Profile
import ru.bredikhinpechnnikov.barter.data.model.Task
import ru.bredikhinpechnnikov.barter.net.getUserData
import ru.bredikhinpechnnikov.barter.ui.new_task.NewTaskActivity

class AppActivity : AppCompatActivity() {
    private var userdata: Profile? = null
    private var taskRecyclerView: RecyclerView? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)

        Log.d("barter", intent.getStringExtra("token") ?: "")
    }

    //    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()

        Log.d("barter", intent.getStringExtra("token") ?: "")

        val token = intent.getStringExtra("token")
        userdata = getUserData(token!!)

        findViewById<TextView>(R.id.username).text = "${userdata!!.firstName} ${userdata!!.lastName}"
        findViewById<TextView>(R.id.balance).text = "${userdata!!.points} б."
        findViewById<TextView>(R.id.rating).text = "${userdata!!.ratingAsCustomer}/${userdata!!.ratingAsExecutor}"

        taskRecyclerView = findViewById(R.id.tasks_for_user_list)
        taskRecyclerView!!.layoutManager = LinearLayoutManager(this).also {
            it.orientation = LinearLayoutManager.VERTICAL
        }
//        taskRecyclerView!!.adapter = TaskListAdapter(getTasks())

        findViewById<Button>(R.id.new_task).setOnClickListener {
            val intent = Intent(applicationContext, NewTaskActivity::class.java)
            intent.putExtra("token", token)
            startActivity(intent)
        }
    }

    private fun getTasks(): List<Task> {
        TODO("Not yet implemented")
    }


}