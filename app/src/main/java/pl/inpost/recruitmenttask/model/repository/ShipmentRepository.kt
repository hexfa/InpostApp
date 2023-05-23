package pl.inpost.recruitmenttask.model.repository

import dagger.hilt.android.scopes.ActivityRetainedScoped
import io.reactivex.Single
import pl.inpost.recruitmenttask.model.api.ShipmentApi
import pl.inpost.recruitmenttask.model.local.ShipmentsResponse
import javax.inject.Inject

@ActivityRetainedScoped
class ShipmentRepository @Inject constructor(private val apiService: ShipmentApi) {
    fun getShipments(): Single<ShipmentsResponse>? = apiService.getShipments()
}