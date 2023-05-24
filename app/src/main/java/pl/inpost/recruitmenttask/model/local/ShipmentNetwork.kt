package pl.inpost.recruitmenttask.model.local

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import pl.inpost.recruitmenttask.model.local.db.TypeConverter
import java.time.ZonedDateTime

@Entity(tableName = "shipments")

data class ShipmentNetwork(

    var id: Int = 0,
    @PrimaryKey
    val number: String,
    val expiryDate: ZonedDateTime?,
    val storedDate: ZonedDateTime?,
    val pickUpDate: ZonedDateTime?,
    val openCode: String?,
    val shipmentType: String?,
    val status: String?,
    @TypeConverters(TypeConverter::class)
    val eventLog: List<EventLogNetwork>?,
    @Embedded(prefix = "sender_")
    val sender: CustomerNetwork?,
    @Embedded(prefix = "receiver_")
    val receiver: CustomerNetwork?,
    @Embedded(prefix = "operations_")
    val operations: OperationsNetwork?
){
    fun getShipmentStatus(): ShipmentStatus {
        return ShipmentStatus.fromString(status)
    }
}
