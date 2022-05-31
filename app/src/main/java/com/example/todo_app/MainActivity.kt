package com.example.todo_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var bottomNavigation:BottomNavigationView
    lateinit var addButton: FloatingActionButton
    var todoListFragment=TodoListFragment()
    var settingsFragment=SettingsFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addButton=findViewById(R.id.add)
        addButton.setOnClickListener{
            showAddBottomSheet()
        }
        bottomNavigation=findViewById(R.id.bottom_nav_bar)
        bottomNavigation.setOnItemSelectedListener { menuItem->
            if (menuItem.itemId==R.id.item_todoList){
                pushFragment(todoListFragment)
            }else if (menuItem.itemId==R.id.item_settings){
                pushFragment(settingsFragment)
            }
            return@setOnItemSelectedListener true;
        }
        bottomNavigation.selectedItemId=R.id.item_todoList
    }

    private fun showAddBottomSheet() {
        val addTodoBottomSheet=AddTodoBottomSheet()
        addTodoBottomSheet.show(supportFragmentManager,"")
        addTodoBottomSheet.onTodoAddedListener=object :AddTodoBottomSheet.OnTodoAddedListener{
            override fun onTodoAdded() {
                if(todoListFragment.isVisible)
                todoListFragment.getTodosListFromDB()
            }

        }
    }

    fun pushFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit()
    }
}