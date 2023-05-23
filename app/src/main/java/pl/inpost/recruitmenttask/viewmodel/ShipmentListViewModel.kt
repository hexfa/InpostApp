package pl.inpost.recruitmenttask.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import pl.inpost.recruitmenttask.model.repository.ShipmentRepository
import pl.inpost.recruitmenttask.model.local.ShipmentNetwork
import pl.inpost.recruitmenttask.model.local.ShipmentsResponse
import javax.inject.Inject

const val TAG="ShipmentListViewModel"
@HiltViewModel
class ShipmentListViewModel @Inject constructor(
    private val shipmentRepository: ShipmentRepository
) : BaseViewModel() {

    private var shipmentItemList:MutableLiveData<ShipmentsResponse> = MutableLiveData()
    private val mutableViewState = MutableLiveData<List<ShipmentNetwork>>(emptyList())
    val viewState: LiveData<List<ShipmentNetwork>> = mutableViewState

    init {
        refreshData()
    }

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

    @OptIn(DelicateCoroutinesApi::class)
    private fun refreshData() {
        GlobalScope.launch(Dispatchers.IO) {
            val shipments = shipmentRepository.getShipments()
            //mutableViewState.setState { shipments }
        }
    }
}
