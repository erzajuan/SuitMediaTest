package com.bastilla.suitmediaapp.thirdscreen

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bastilla.suitmediaapp.api.ApiConfig
import com.bastilla.suitmediaapp.api.DataItem
import com.bastilla.suitmediaapp.api.UserResponse
import retrofit2.*

class ThirdScreenViewModel : ViewModel() {
    private val _listUser = MutableLiveData<List<DataItem>>()
    val listUser: LiveData<List<DataItem>> = _listUser

    init {
        getUser()
    }

//    private fun getUser(): LiveData<PagingData<DataItem>> =
//        repository.getPaggingStories().cachedIn(viewModelScope)

    private fun getUser() {
        val client = ApiConfig.getApiService().getUser()
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    _listUser.value = response.body()?.data
                } else {
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }
}