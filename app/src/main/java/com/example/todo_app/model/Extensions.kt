package com.example.todo_app.model

import java.util.*

fun Calendar.clearTime():Calendar{
    this.clear(Calendar.HOUR)
    this.clear(Calendar.MINUTE)
    this.clear(Calendar.SECOND)
    this.clear(Calendar.MILLISECOND)
    return this
}