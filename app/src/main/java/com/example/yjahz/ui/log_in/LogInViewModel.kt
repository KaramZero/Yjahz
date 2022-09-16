package com.example.yjahz.ui.log_in

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yjahz.model.Status
import com.example.yjahz.model.user.User
import com.example.yjahz.remote_source.AuthRemoteSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LogInViewModel @Inject constructor() : ViewModel() {


    @Inject
    lateinit var remoteSource: AuthRemoteSource

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    var user = User()
    private val _logInStatus = MutableLiveData<Status?>()
    val logInStatus: LiveData<Status?> = _logInStatus


    fun logIn(email: String = "null", password: String = "null") {
        _logInStatus.postValue(Status.LOADING)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                user = remoteSource.logIn(email, password)
                sharedPreferences.edit().putString("token", user.token).apply()
                _logInStatus.postValue(Status.DONE)
            } catch (ex: Exception) {
                Log.e("TAG", "logIn:  $ex")
                _logInStatus.postValue(Status.ERROR)
            }
        }
    }


}