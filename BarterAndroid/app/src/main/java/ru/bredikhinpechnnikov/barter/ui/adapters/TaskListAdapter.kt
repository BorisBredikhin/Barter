package ru.bredikhinpechnnikov.barter.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.bredikhinpechnnikov.barter.R
import ru.bredikhinpechnnikov.barter.data.model.Task
import ru.bredikhinpechnnikov.barter.ui.TaskView

class TaskListAdapter(private val tasks: List<Task>, private val token: String) : RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View, val token: String) : RecyclerView.ViewHolder(itemView) {
        fun update(task: Task) {
            this.task = task
            id = task.id!!
            taskTitle!!.text = task.title
            taskDescription!!.text = task.description
            taskCustomer!!.text = "Заказчик: " // todo add customer's name
            taskPrice!!.text = "Цена: ${task.price} баллов"
            taskStatus!!.text = "Статус: ${task.status}"
            taskAddress!!.text = task.address

            chooseBtn!!.setOnClickListener {
                val intent = Intent(itemView.context, TaskView::class.java)
                intent.putExtra("task", task.id)
                intent.putExtra("token", token)
                itemView.context.startActivity(intent)
            }
        }

        var taskTitle: TextView? = null
        var taskDescription: TextView? = null
        var taskCustomer: TextView? = null
        var taskPrice: TextView? = null
        var taskAddress: TextView? = null
        var taskStatus: TextView? = null
        var chooseBtn: Button? = null
        var task: Task? = null
        var id: Int = -1

        init {
            taskTitle = itemView.findViewById(R.id.task_title)
            taskDescription = itemView.findViewById(R.id.task_description)
            taskCustomer = itemView.findViewById(R.id.task_customer)
            taskPrice = itemView.findViewById(R.id.task_price)
            taskAddress = itemView.findViewById(R.id.task_address)
            taskStatus = itemView.findViewById(R.id.task_status)
            chooseBtn = itemView.findViewById(R.id.choose_task_btn)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.tasklist_item_layout, parent, false)
        return ViewHolder(itemView, token)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.update(tasks[position])
    }

    override fun getItemCount(): Int = tasks.size
}