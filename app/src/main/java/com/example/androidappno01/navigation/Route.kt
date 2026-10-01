package com.example.androidappno01.navigation

object Route {
    const val HOME = "home"
    const val DETAIL = "detail/{noteId}"
    fun detail(id: Long) = "detail/$id"
}
