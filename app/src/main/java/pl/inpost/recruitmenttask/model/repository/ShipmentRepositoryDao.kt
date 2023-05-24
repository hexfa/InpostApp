package pl.inpost.recruitmenttask.model.repository

import pl.inpost.recruitmenttask.model.local.ShipmentNetwork
import pl.inpost.recruitmenttask.model.local.ShipmentsResponse
import pl.inpost.recruitmenttask.model.local.db.dao.ShipmentNetworkDao
import javax.inject.Inject

class ShipmentRepositoryDao @Inject
constructor(
    private val dao: ShipmentNetworkDao
) {
    suspend fun getAllItem(): List<ShipmentNetwork> = dao.getAllShipments()

    fun updateItem(shipmentItem: ShipmentNetwork) = dao.update(shipmentItem)

    suspend fun insert(shipmentItem: ShipmentNetwork) = dao.insertShipments(shipmentItem)


    suspend fun insertItems(shipmentItem: ShipmentsResponse) =
        dao.insertItems(shipmentItem.shipments)
}