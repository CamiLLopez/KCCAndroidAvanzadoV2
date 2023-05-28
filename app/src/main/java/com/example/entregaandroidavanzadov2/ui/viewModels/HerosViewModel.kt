package com.example.entregaandroidavanzadov2.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.entregaandroidavanzadov2.SuperHero
import com.example.entregaandroidavanzadov2.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HerosViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val _locationResult = MutableLiveData<Boolean>()
    val locationResult: LiveData<Boolean> = _locationResult

    private val _heroResult = MutableLiveData<SuperHero>()
    val heroResult: LiveData<SuperHero> = _heroResult

    private val _heros = MutableLiveData<List<SuperHero>>()
    val heros: LiveData<List<SuperHero>> get() = _heros


    fun getHeros() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getHeros()
            }
            _heros.value = result
        }
    }

    fun getLocationsByHero(heroID: String){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                repository.getLocations(heroID)

                }
                result.let {
                    _locationResult.postValue(result != null)
                }
        }
    }

    fun getHero(heroID: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                repository.getHero(heroID)

            }
            result.let {
                _heroResult.postValue(result)
            }
        }
    }
}