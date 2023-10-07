package com.example.suprememath.utils

import android.content.Context


class Prefs(context: Context) {

    private val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)


        fun saveState(){
            prefs.edit().putBoolean("isShown",true).apply()
        }
        fun isShown():Boolean{
            return prefs.getBoolean("isShown",false)
        }


}