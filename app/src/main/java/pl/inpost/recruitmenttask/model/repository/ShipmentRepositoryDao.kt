package pl.inpost.recruitmenttask.model.repository

import pl.inpost.recruitmenttask.model.local.ShipmentNetwork
import pl.inpost.recruitmenttask.model.local.db.dao.ShipmentNetworkDao
import javax.inject.Inject

class ShipmentRepositoryDao@Inject
constructor(
    private val dao: ShipmentNetworkDao
) {
    fun getAllItem() = dao.getAllItems()

    fun updateItem(shipmentItem: ShipmentNetwork) = dao.update(shipmentItem)

    fun insert(shipmentItem: ShipmentNetwork) = dao.insert(shipmentItem)

    fun delete(shipmentItem: ShipmentNetwork) = dao.delete(shipmentItem)

    fun insertItems(shipmentItem: List<ShipmentNetwork>) = dao.insertItems(shipmentItem)
}