package com.example.todo_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_app.model.Todo

class TodoAdapter(var todoList: MutableList<Todo>?):RecyclerView.Adapter<TodoAdapter.ViewHolder> (){
    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val name:TextView=view.findViewById(R.id.title)
        val details:TextView=view.findViewById(R.id.description)
        val markAsDone:ImageView=view.findViewById(R.id.mark_as_done_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_todo_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item=todoList?.get(position)
        holder.name.setText(item?.name)
        holder.details.setText(item?.details)
    }

    override fun getItemCount(): Int =todoList?.size ?:0

    fun changeData(list:MutableList<Todo>){
        todoList=list
        notifyDataSetChanged()
    }
}