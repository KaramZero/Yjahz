package com.example.yjahz.ui.welcome

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yjahz.model.pojo.Status
import com.example.yjahz.model.pojo.User
import com.example.yjahz.network.Keys
import com.example.yjahz.remote_source.AuthRemoteSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WelcomeViewModel @Inject constructor()  : ViewModel() {

    private var remoteSource = AuthRemoteSource.getInstance()

    private val _clientStatus = MutableLiveData<Status>()
    val clientStatus: LiveData<Status> = _clientStatus

    lateinit var sharedPreferences :SharedPreferences
    var user = User()

    fun getClient() {
        _clientStatus.postValue(Status.LOADING)

        sharedPreferences.getString("token","null")?.apply {
            Keys.AuthorizationToken = "Bearer ${this.trim()}"
        }


        viewModelScope.launch(Dispatchers.Main) {
            try {
                user = remoteSource.getClientProfile()
                Log.d("TAG", "getClient: $user ")
                _clientStatus.postValue(Status.DONE)
            } catch (ex: Exception) {
                Log.d("TAG", "getClient: $ex ")
                _clientStatus.postValue(Status.ERROR)
            }
        }
    }

}