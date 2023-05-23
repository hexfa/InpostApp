package pl.inpost.recruitmenttask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import pl.inpost.recruitmenttask.model.api.ShipmentApi
import pl.inpost.recruitmenttask.model.local.ShipmentNetwork
import pl.inpost.recruitmenttask.utils.setState
import javax.inject.Inject

@HiltViewModel
class ShipmentListViewModel @Inject constructor(
    private val shipmentApi: ShipmentApi
) : ViewModel() {

    private val mutableViewState = MutableLiveData<List<ShipmentNetwork>>(emptyList())
    val viewState: LiveData<List<ShipmentNetwork>> = mutableViewState

    init {
        refreshData()
    }

    private fun refreshData() {
        GlobalScope.launch(Dispatchers.Main) {
            val shipments = shipmentApi.getShipments()
            mutableViewState.setState { shipments }
        }
    }
}
