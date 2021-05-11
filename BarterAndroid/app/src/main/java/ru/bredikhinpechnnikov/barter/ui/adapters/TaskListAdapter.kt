package ru.bredikhinpechnnikov.barter.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.bredikhinpechnnikov.barter.R
import ru.bredikhinpechnnikov.barter.data.model.Task

class TaskListAdapter(private val tasks: List<Task>) : RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var item : Task? = null
            set(value) {
                // TODO: 20.04.2021 display task
            }
        init {
            TODO("set views to properties")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.tasklist_item_layout, parent,  false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.item = tasks[position]
    }

    override fun getItemCount(): Int =tasks.size
}