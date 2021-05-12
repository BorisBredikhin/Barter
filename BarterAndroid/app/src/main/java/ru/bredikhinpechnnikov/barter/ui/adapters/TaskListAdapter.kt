package ru.bredikhinpechnnikov.barter.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.bredikhinpechnnikov.barter.R
import ru.bredikhinpechnnikov.barter.data.model.Task

class TaskListAdapter(private val tasks: List<Task>) : RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun update(task: Task) {
            id = task.id!!
            taskTitle!!.text = task.title
            taskDescription!!.text = task.description
            taskCustomer!!.text = "Заказчик: " // todo add customer's name
            taskPrice!!.text = "Цена: ${task.price} баллов"
            taskAddress!!.text = task.address
        }

        var taskTitle: TextView? = null
        var taskDescription: TextView? = null
        var taskCustomer: TextView? = null
        var taskPrice: TextView? = null
        var taskAddress: TextView? = null
        var chooseBtn: Button? = null
        var id: Int = -1

        init {
            taskTitle = itemView.findViewById(R.id.task_title)
            taskDescription = itemView.findViewById(R.id.task_description)
            taskCustomer = itemView.findViewById(R.id.task_customer)
            taskPrice = itemView.findViewById(R.id.task_price)
            taskAddress = itemView.findViewById(R.id.task_address)
            chooseBtn = itemView.findViewById(R.id.choose_task_btn)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.tasklist_item_layout, parent,  false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.update(tasks[position])
    }

    override fun getItemCount(): Int =tasks.size
}