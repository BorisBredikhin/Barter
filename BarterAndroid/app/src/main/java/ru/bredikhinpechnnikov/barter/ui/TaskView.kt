package ru.bredikhinpechnnikov.barter.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import ru.bredikhinpechnnikov.barter.R
import ru.bredikhinpechnnikov.barter.data.model.Task
import ru.bredikhinpechnnikov.barter.net.TaskProvider
import ru.bredikhinpechnnikov.barter.userToken

class TaskView : AppCompatActivity() {
    private var task_id: Int =-1
    private var task: Task? = null
    private var TaskTitleView: TextView? = null
    private var TaskDescriptionView: TextView? = null
    private var TaskPriceView: TextView? = null
    private var TaskAddressView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_view)

        task_id = intent.getIntExtra("task", -1)

        task = TaskProvider.getTask(intent.getStringExtra("token")!!, task_id)

        TaskTitleView = findViewById(R.id.task_title)
        TaskDescriptionView = findViewById(R.id.task_description)
        TaskPriceView = findViewById(R.id.task_price)
        TaskAddressView = findViewById(R.id.task_address)

        update()
    }

    fun update(){
        TaskTitleView!!.text = "Заказ №${task_id}: ${task!!.title}"
        TaskDescriptionView!!.text = task!!.description
        TaskPriceView!!.text = "${task!!.price} б."
        TaskAddressView!!.text = task!!.address
    }
}