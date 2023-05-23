package pl.inpost.recruitmenttask.model.api

import pl.inpost.recruitmenttask.model.ShipmentNetwork

interface ShipmentApi {
    suspend fun getShipments(): List<ShipmentNetwork>
}
