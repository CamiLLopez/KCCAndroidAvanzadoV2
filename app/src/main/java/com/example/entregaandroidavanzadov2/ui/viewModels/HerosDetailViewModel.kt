package com.example.entregaandroidavanzadov2.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.entregaandroidavanzadov2.LocationsHero
import com.example.entregaandroidavanzadov2.SuperHero
import com.example.entregaandroidavanzadov2.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
@HiltViewModel
class HerosDetailViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val _locationResult = MutableLiveData<List<LocationsHero>?>()
    val locationResult: LiveData<List<LocationsHero>?> = _locationResult

    private val _heroResult = MutableLiveData<SuperHero>()
    val heroResult: LiveData<SuperHero> = _heroResult

    private val _favoriteResult = MutableLiveData<Boolean>()
    val favoriteResult: LiveData<Boolean> = _favoriteResult

    fun getLocationsByHero(heroID: String){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                repository.getLocations(heroID)
                }
                result.let {
                    _locationResult.postValue(result)
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
    fun markFavoriteHero(heroID: String, favorite: Boolean){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.markFavoriteHero(heroID, favorite)
            }
        }
    }
}