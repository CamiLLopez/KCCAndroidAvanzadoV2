package com.example.entregaandroidavanzadov2.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.entregaandroidavanzadov2.data.RepositoryLogin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Credentials
import javax.inject.Inject
@HiltViewModel
class LoginViewModel @Inject constructor(private val repository : RepositoryLogin): ViewModel() {

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult
    fun login(user: String, password: String){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                repository.login(Credentials.basic(user, password))
            }
            result.let {
                _loginResult.postValue(result != null)
            }
        }
    }
}