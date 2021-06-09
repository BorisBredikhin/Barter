package ru.bredikhinpechnnikov.barter.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.bredikhinpechnnikov.barter.R
import ru.bredikhinpechnnikov.barter.net.TaskProvider.getMyTasks
import ru.bredikhinpechnnikov.barter.ui.adapters.TaskListAdapter

class MyTasks : AppCompatActivity() {
    lateinit var taskRecyclerView: RecyclerView
    var token: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_tasks)
    }

    override fun onStart() {
        super.onStart()
        token = intent.getStringExtra("token")
        taskRecyclerView = findViewById(R.id.my_tasks_list)
        taskRecyclerView.layoutManager = LinearLayoutManager(this).also {
            it.orientation = LinearLayoutManager.VERTICAL
        }
        taskRecyclerView.adapter = TaskListAdapter(getMyTasks(token!!), token!!)
    }
}