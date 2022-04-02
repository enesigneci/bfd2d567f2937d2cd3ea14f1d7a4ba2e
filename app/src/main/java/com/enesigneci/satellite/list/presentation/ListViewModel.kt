package com.enesigneci.satellite.list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesigneci.satellite.list.data.db.model.SatelliteList
import com.enesigneci.satellite.list.domain.ListSatellitesUseCase
import javax.inject.Inject
import kotlinx.coroutines.launch

class ListViewModel @Inject constructor(
    private val listSatellitesUseCase: ListSatellitesUseCase
): ViewModel() {
    private val _uiLiveData = MutableLiveData<List<SatelliteList>>()
    val uiLiveData: LiveData<List<SatelliteList>> = _uiLiveData

    fun loadSatellites() {
        viewModelScope.launch {
            _uiLiveData.postValue(listSatellitesUseCase.loadSatellites())
        }
    }
}