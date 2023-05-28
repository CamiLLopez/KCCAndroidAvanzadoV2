package com.example.entregaandroidavanzadov2.ui.viewModels

import android.util.Base64
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.entregaandroidavanzadov2.data.RepositoryLogin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository : RepositoryLogin): ViewModel() {

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult


    fun login(user: String, password: String){

        val stringAuthorization = "$user:$password"
        val encoder = Base64.encodeToString(stringAuthorization.toByteArray(), Base64.DEFAULT)
        val cleanedString = encoder.replace("\n", "")

        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                repository.login("Basic $cleanedString")
            }
            result.let {
                _loginResult.postValue(result != null)
            }
        }
    }
}