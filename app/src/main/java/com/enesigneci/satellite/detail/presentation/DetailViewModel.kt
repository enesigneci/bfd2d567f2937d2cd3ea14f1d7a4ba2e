package com.enesigneci.satellite.detail.presentation

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
class DetailViewModel @Inject constructor(
    private val stringProvider: StringProvider
): ViewModel() {
    private val _uiLiveData = MutableLiveData<Resource<List<SatelliteList>>>()
    val uiLiveData: LiveData<Resource<List<SatelliteList>>> = _uiLiveData
}