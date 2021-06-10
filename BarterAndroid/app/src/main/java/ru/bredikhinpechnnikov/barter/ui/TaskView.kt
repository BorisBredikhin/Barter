package ru.bredikhinpechnnikov.barter.ui

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
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
    private var execute_btn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_view)

        val token = intent.getStringExtra("token")!!
        val my_task = intent.getBooleanExtra("my_task", false)

        task_id = intent.getIntExtra("task", -1)

        task = TaskProvider.getTask(token, task_id)

        TaskTitleView = findViewById(R.id.task_title)
        TaskDescriptionView = findViewById(R.id.task_description)
        TaskPriceView = findViewById(R.id.task_price)
        TaskAddressView = findViewById(R.id.task_address)
        execute_btn = findViewById(R.id.execute_btn)

        if (my_task && task!!.status != "Исполнен") {
            with(execute_btn!!) {
                text = "Подтвердить исполнение"
                setOnClickListener {
                    TaskProvider.confirm(token, task!!)
                }
            }
        } else if (task!!.status == "Новый") {
            execute_btn!!.setOnClickListener {
                TaskProvider.execute_task(task_id, token)
            }
        } else if (task!!.status == "Исполнен") {
            (execute_btn!!.parent as ViewGroup).removeView(execute_btn!!)
        }

        update()
    }

    @SuppressLint("SetTextI18n")
    fun update(){
        TaskTitleView!!.text = "Заказ №${task_id}: ${task!!.title}"
        TaskDescriptionView!!.text = "Заказчик: ${task!!.customer_str}\n${task!!.description}"
        TaskPriceView!!.text = "${task!!.price} б."
        TaskAddressView!!.text = task!!.address
    }
}