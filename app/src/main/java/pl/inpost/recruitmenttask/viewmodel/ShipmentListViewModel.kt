package pl.inpost.recruitmenttask.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pl.inpost.recruitmenttask.model.repository.ShipmentRepository
import pl.inpost.recruitmenttask.model.local.ShipmentNetwork
import pl.inpost.recruitmenttask.model.local.ShipmentsResponse
import pl.inpost.recruitmenttask.model.repository.ShipmentRepositoryDao
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

const val TAG="ShipmentListViewModel"
@HiltViewModel
class ShipmentListViewModel @Inject constructor(
    private val shipmentRepository: ShipmentRepository,
    private val shipmentRepositoryDao: ShipmentRepositoryDao
) : BaseViewModel() {

    private var shipmentItemList:MutableLiveData<ShipmentsResponse> = MutableLiveData()
    private val _shipmentLiveData = MutableLiveData<List<ShipmentNetwork>>()
    val shipmentLiveData: LiveData<List<ShipmentNetwork>> get() = _shipmentLiveData

    init {
        refreshData()
    }

    fun getShipment(): LiveData<ShipmentsResponse> {
        val shipmentItemList = MutableLiveData<ShipmentsResponse>()

        // Use coroutine scope for suspending function calls
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    suspendCoroutine<ShipmentsResponse> { continuation ->
                        shipmentRepository.getShipments()
                            ?.subscribeOn(Schedulers.io())
                            ?.observeOn(AndroidSchedulers.mainThread())
                            ?.subscribe(object : SingleObserver<ShipmentsResponse> {
                                override fun onSubscribe(d: Disposable) {
                                    compositeDisposable.add(d)
                                    Log.e(TAG, "onSubscribe: ")
                                }

                                override fun onError(e: Throwable) {
                                    continuation.resumeWithException(e)
                                    Log.e(TAG, "onError: ")
                                }

                                override fun onSuccess(t: ShipmentsResponse) {
                                    continuation.resume(t)
                                    Log.e(TAG, "onSuccess: ")
                                }
                            })
                    }
                }

                // Update the live data with the response
                shipmentItemList.postValue(response)
            } catch (e: Exception) {
                // Handle any exceptions and log errors
                Log.e(TAG, "Exception: ${e.message}")
            }
        }

        return shipmentItemList
    }



/*
    fun getShipment():LiveData<ShipmentsResponse>{
        shipmentRepository.getShipments()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object :SingleObserver<ShipmentsResponse>{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                    Log.e(TAG, "onSubscribe: ")

                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "onError: ")
                }

                override fun onSuccess(t: ShipmentsResponse) {
                    Log.e(TAG, "onSuccess: ")

                    shipmentItemList.value=t
                }

            })
        return shipmentItemList

    }
*/


    fun addItems(shipmentItems: List<ShipmentNetwork>) {
        viewModelScope.launch(Dispatchers.IO) {
            shipmentRepositoryDao.insertItems(shipmentItems)
        }
    }

    fun updateItem(shipmentItems: ShipmentNetwork) {
        viewModelScope.launch(Dispatchers.IO) {
            shipmentRepositoryDao.updateItem(shipmentItems)
        }
    }

    fun deleteItem(shipmentItems: ShipmentNetwork) {
        viewModelScope.launch(Dispatchers.IO) {
            shipmentRepositoryDao.delete(shipmentItems)
        }
    }

    /*fun onUndoDelete(shipmentItems: ShipmentNetwork) {
        viewModelScope.launch(Dispatchers.IO) {
            shipmentRepositoryDao.insert(shipmentItems)
        }
    }*/

    fun addItem(shipmentItems: ShipmentNetwork) {
        viewModelScope.launch(Dispatchers.IO) {
            shipmentRepositoryDao.insert(shipmentItems)
        }
    }

    fun getItemDataBase(){
        viewModelScope.launch ( Dispatchers.IO) {
            shipmentRepositoryDao.getAllItem()
                .collect { items ->
                    _shipmentLiveData.postValue(items)
                }
        }
    }



    private fun refreshData() {
        GlobalScope.launch(Dispatchers.Main) {
            val shipments = shipmentRepository.getShipments()
            //mutableViewState.setState { shipments }
        }
    }
}
