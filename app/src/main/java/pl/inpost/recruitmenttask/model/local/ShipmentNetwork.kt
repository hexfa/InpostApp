package pl.inpost.recruitmenttask.model.local

import pl.inpost.recruitmenttask.model.local.CustomerNetwork
import pl.inpost.recruitmenttask.model.local.EventLogNetwork
import pl.inpost.recruitmenttask.model.local.OperationsNetwork
import java.time.ZonedDateTime

data class ShipmentNetwork(
    val number: String,
    val shipmentType: String,
    val status: String,
    val eventLog: List<EventLogNetwork>,
    val openCode: String?,
    val expiryDate: ZonedDateTime?,
    val storedDate: ZonedDateTime?,
    val pickUpDate: ZonedDateTime?,
    val receiver: CustomerNetwork?,
    val sender: CustomerNetwork?,
    val operations: OperationsNetwork
)
