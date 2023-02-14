package com.example.suprememath.ui.mathApi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.suprememath.models.Image
import com.example.suprememath.remote.ApiService
import com.example.suprememath.remote.RetrofitBackendClient
import com.example.suprememath.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MathViewModel:ViewModel() {

    private val apiService: ApiService by lazy{
        RetrofitClient.create()
    }
    private val apiServiceBack: ApiService by lazy{
        RetrofitBackendClient.create()
    }

    fun mathExpress(expr: String): LiveData<Number> {
        return getMath(expr)
    }
    fun getBackEnd(): LiveData<Image> {
        return getBack()
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


    private fun getBack(): MutableLiveData<Image> {
        val data = MutableLiveData<Image>()

        apiServiceBack.getBack().enqueue(object:
            Callback<Image> {
            override fun onResponse(call: Call<Image>, response: Response<Image>) {
               data.postValue(response.body())
            }

            override fun onFailure(call: Call<Image>, t: Throwable) {
                Log.e("myBackend","onFail: ${t.message}")
            }


        })
        return data
    }
}