package pl.inpost.recruitmenttask.network.api

import pl.inpost.recruitmenttask.network.model.ShipmentNetwork

interface ShipmentApi {
    suspend fun getShipments(): List<ShipmentNetwork>
}
