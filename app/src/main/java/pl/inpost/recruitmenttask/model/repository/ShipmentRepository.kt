package pl.inpost.recruitmenttask.model.repository

import dagger.hilt.android.scopes.ActivityRetainedScoped
import pl.inpost.recruitmenttask.model.api.ShipmentApi
import javax.inject.Inject

@ActivityRetainedScoped
class ShipmentRepository @Inject constructor(private val apiService: ShipmentApi) {

    suspend fun getShipments() = apiService.getShipments()
}