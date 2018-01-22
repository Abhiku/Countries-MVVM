package com.example.mylibrary

import android.util.Log

/**
 * Created by abhijeet on 19/1/18.
 */
class LibraryClass{
    fun printLog(string: String){
        Log.i(this.javaClass.simpleName, string)
    }
}