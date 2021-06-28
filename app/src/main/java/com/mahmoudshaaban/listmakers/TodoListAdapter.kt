package com.mahmoudshaaban.listmakers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TodoListAdapter(val lists : ArrayList<TaskList>) : RecyclerView.Adapter<TodoListViewHolder>() {

    


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.todolist_items
            , parent, false
        )
        return TodoListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        holder.listPositionTextview.text = (position + 1).toString()
        holder.listTitleTextview.text = lists[position].name
    }

    fun addlist(list: TaskList) {
        lists.add(list)
        //notifyItemInserted(lists.size-1)

    }

}