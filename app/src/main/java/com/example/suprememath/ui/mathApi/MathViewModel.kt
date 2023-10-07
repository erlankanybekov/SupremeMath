package com.example.suprememath.ui.mathApi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.suprememath.base.BaseViewModel
import com.example.suprememath.remote.ApiService
import com.example.suprememath.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MathViewModel: BaseViewModel() {

    private val apiService: ApiService by lazy{
        RetrofitClient.create()
    }

    fun mathExpress(expr: String): LiveData<Number> {
        return getMath(expr)
    }

    private fun getMath(expr:String): MutableLiveData<Number> {
        val data = MutableLiveData<Number>()

        apiService.getMath(expr).enqueue(object:
            Callback<Number> {
            override fun onResponse(call: Call<Number>, response: Response<Number>) {
                data.postValue(response.body())
            }

            override fun onFailure(call: Call<Number>, t: Throwable) {
                Log.e("ololo","onFail: ${t.message}")
            }


        })
        return data
    }
}