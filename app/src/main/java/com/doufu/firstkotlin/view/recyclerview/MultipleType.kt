package com.doufu.firstkotlin.view.recyclerview

interface MultipleType<in  T> {
    fun getLayoutId(item: T, position: Int): Int
}
