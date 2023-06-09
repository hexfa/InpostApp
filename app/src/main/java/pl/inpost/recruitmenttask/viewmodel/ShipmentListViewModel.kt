package pl.inpost.recruitmenttask.viewmodel

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import pl.inpost.recruitmenttask.model.local.ShipmentNetwork
import pl.inpost.recruitmenttask.model.local.ShipmentsResponse
import pl.inpost.recruitmenttask.model.repository.ShipmentRepository
import pl.inpost.recruitmenttask.model.repository.ShipmentRepositoryDao
import javax.inject.Inject

const val TAG = "ShipmentListViewModel"

@HiltViewModel
class ShipmentListViewModel @Inject constructor(
    private val shipmentRepository: ShipmentRepository,
    private val shipmentRepositoryDao: ShipmentRepositoryDao
) : ViewModel() {
    val shipmentNetworksLocalLiveData: MutableLiveData<List<ShipmentNetwork>> = MutableLiveData()
    val shipmenDetailLiveData: MutableLiveData<ShipmentNetwork> = MutableLiveData()

    init {
        refreshData()
    }

    val shipmentNetworksRemoteLiveData = MutableLiveData<ShipmentsResponse?>()

    fun getShipment() = liveData(Dispatchers.IO) {
        try {
            emitSource(shipmentNetworksRemoteLiveData) // Emit current MutableLiveData

            val response = shipmentRepository.getShipments() // suspend function call

            // Update the live data with the response
            shipmentNetworksRemoteLiveData.postValue(response)
        } catch (e: Exception) {
            // Handle any exceptions and log errors
            Log.e(TAG, "Exception: ${e.message}")
        }
    }
    fun getShipments() {
        viewModelScope.launch(Dispatchers.IO) {
            shipmentNetworksRemoteLiveData.postValue(shipmentRepository.getShipments() )
        }
    }
    fun addItems(shipmentItems: ShipmentsResponse) {
        viewModelScope.launch(Dispatchers.IO) {
            shipmentRepositoryDao.insertItems(shipmentItems)
        }
    }

    fun getShipmentOfLocal() {
        viewModelScope.launch(Dispatchers.IO) {
            shipmentNetworksLocalLiveData.postValue(shipmentRepositoryDao.getAllItem())
        }


    }

    fun updateShipment(shipmentItem: ShipmentNetwork) {
        viewModelScope.launch(Dispatchers.IO) {
            shipmentRepositoryDao.updateItem(shipmentItem)
        }
    }


    fun addItem(shipmentItems: ShipmentNetwork) {
        viewModelScope.launch(Dispatchers.IO) {
            shipmentRepositoryDao.insert(shipmentItems)
        }
    }


    @OptIn(DelicateCoroutinesApi::class)
    private fun refreshData() {
        GlobalScope.launch(Dispatchers.Main) {
            val shipments = shipmentRepository.getShipments()
            //mutableViewState.setState { shipments }
        }
    }
}
