package com.enesigneci.satellite.list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesigneci.satellite.R
import com.enesigneci.satellite.base.StringProvider
import com.enesigneci.satellite.list.data.Resource
import com.enesigneci.satellite.list.data.db.model.SatelliteList
import com.enesigneci.satellite.list.domain.ListSatellitesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class ListViewModel @Inject constructor(
    private val stringProvider: StringProvider,
    private val listSatellitesUseCase: ListSatellitesUseCase
): ViewModel() {
    private val _uiLiveData = MutableLiveData<Resource<List<SatelliteList>>>()
    val uiLiveData: LiveData<Resource<List<SatelliteList>>> = _uiLiveData

    fun loadSatellites() {
        viewModelScope.launch {
            _uiLiveData.postValue(Resource.Loading)
            with(listSatellitesUseCase.loadSatellites()) {
                if (isEmpty()) {
                    _uiLiveData.postValue(Resource.Error(Exception(stringProvider.getString(R.string.there_is_no_satellite))))
                } else {
                    _uiLiveData.postValue(Resource.Success(this))
                }
            }
        }
    }
}