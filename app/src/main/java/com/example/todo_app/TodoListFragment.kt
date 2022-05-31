package com.example.todo_app

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_app.database.MyDataBase
import com.example.todo_app.model.clearTime
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import java.util.*

class TodoListFragment:Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var calendarView:MaterialCalendarView
    val adapter=TodoAdapter(null)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_todo_list,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        Log.v("data:::",MyDataBase.getInstance(requireContext()).todoDao().getAllTodo().toString())
    }

    override fun onResume() {
        super.onResume()
        getTodosListFromDB()
    }
    var calendar=Calendar.getInstance()
     fun getTodosListFromDB() {
        val todoList=MyDataBase.getInstance(requireContext()).todoDao().getTodoByDate(calendar.clearTime().time)
        adapter.changeData(todoList)
    }

    private fun initViews() {
        recyclerView=requireView().findViewById(R.id.todo_recycler)
        calendarView=requireView().findViewById(R.id.calendarView)
        calendarView.selectedDate= CalendarDay.today()
        recyclerView.adapter=adapter
        calendarView.setOnDateChangedListener{
            widget,calenderDay,selected->
            calendar.set(Calendar.DAY_OF_MONTH,calenderDay.day)
            calendar.set(Calendar.MONTH,calenderDay.month-1)
            calendar.set(Calendar.YEAR,calenderDay.year)
            getTodosListFromDB()
        }
    }
}