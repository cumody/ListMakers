package com.mahmoudshaaban.listmakers

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoListViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview) {

    var listPositionTextview = itemview.findViewById<TextView>(R.id.itemNumber)
    var listTitleTextview = itemview.findViewById<TextView>(R.id.ItemString)
}