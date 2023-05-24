package pl.inpost.recruitmenttask.model.api

import pl.inpost.recruitmenttask.model.local.ShipmentsResponse

interface ShipmentApi {
    suspend fun getShipments(): ShipmentsResponse
}
