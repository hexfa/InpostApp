package pl.inpost.recruitmenttask.model.api

import io.reactivex.Single
import pl.inpost.recruitmenttask.model.local.ShipmentsResponse

interface ShipmentApi {
    fun getShipments(): Single<ShipmentsResponse>?
}
