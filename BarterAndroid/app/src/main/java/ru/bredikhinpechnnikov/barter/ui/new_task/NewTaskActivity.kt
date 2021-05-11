package ru.bredikhinpechnnikov.barter.ui.new_task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import ru.bredikhinpechnnikov.barter.R
import ru.bredikhinpechnnikov.barter.data.model.Task
import ru.bredikhinpechnnikov.barter.net.TaskProvider

class NewTaskActivity : AppCompatActivity() {
    private lateinit var task_title: EditText
    private lateinit var task_description: EditText
    private lateinit var task_price: EditText
    private lateinit var task_address: EditText
    private lateinit var add_btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)

        task_title = findViewById(R.id.new_task_title)
        task_description = findViewById(R.id.new_task_description)
        task_price = findViewById(R.id.new_task_price)
        task_address = findViewById(R.id.new_task_address)
        add_btn = findViewById(R.id.add_new_task)

        add_btn.setOnClickListener {
            val task = Task(
                task_title.text.toString(),
                task_description.text.toString(),
                task_price.text.toString().toInt(),
                task_address.text.toString(),
            )

           TaskProvider.addNewTask(task, intent.getStringExtra("token")!!)
        }
    }
}