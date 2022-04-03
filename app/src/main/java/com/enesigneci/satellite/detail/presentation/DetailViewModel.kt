package com.enesigneci.satellite.detail.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesigneci.satellite.R
import com.enesigneci.satellite.base.StringProvider
import com.enesigneci.satellite.detail.data.model.SatelliteDetail
import com.enesigneci.satellite.detail.data.model.SatelliteDetailUIModel
import com.enesigneci.satellite.detail.domain.DetailUseCase
import com.enesigneci.satellite.list.data.Resource
import com.enesigneci.satellite.list.data.db.model.SatelliteList
import com.enesigneci.satellite.list.domain.ListSatellitesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val stringProvider: StringProvider,
    private val detailUseCase: DetailUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val args = DetailFragmentArgs.fromSavedStateHandle(savedStateHandle)
    private val id = args.id
    private val name = args.name

    private val _uiLiveData = MutableLiveData<Resource<SatelliteDetail>>()
    val uiLiveData: LiveData<Resource<SatelliteDetail>> = _uiLiveData


    private val _uiModelLiveData = MutableLiveData<SatelliteDetailUIModel>()
    val uiModelLiveData: LiveData<SatelliteDetailUIModel> = _uiModelLiveData

    fun getSatelliteDetail() {
        viewModelScope.launch {
            _uiLiveData.postValue(Resource.Loading)
            with(detailUseCase.getSatelliteById(id, name)) {
                if (this.id == null) {
                    _uiLiveData.postValue(Resource.Error(Exception(stringProvider.getString(R.string.couldnt_get_satellite_detail))))
                } else {
                    _uiLiveData.postValue(Resource.Success(this))
                    val satelliteDetailUIModel = SatelliteDetailUIModel(
                        args.name,
                        stringProvider.getString(R.string.height_mass, "$height / $mass"),
                        prepareDate(firstFlight),
                        prepareCost(costPerLaunch),
                        ""
                    )
                    _uiModelLiveData.postValue(satelliteDetailUIModel)
                }
            }
        }
    }

    private fun prepareDate(date: String?): String {
        val inputDate = SimpleDateFormat("yyyy-mm-dd", Locale.getDefault()).parse(date)
        val outputDate = SimpleDateFormat("dd.mm.yyyy", Locale.getDefault()).format(inputDate)
        return outputDate.toString()
    }
    private fun prepareCost(cost: Int?): String {
        return stringProvider.getString(R.string.cost, DecimalFormat("#,###,###", DecimalFormatSymbols().apply { groupingSeparator = '.' }).format(cost))
    }
}