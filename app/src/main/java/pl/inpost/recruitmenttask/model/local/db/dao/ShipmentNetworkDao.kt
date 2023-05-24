package pl.inpost.recruitmenttask.model.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import pl.inpost.recruitmenttask.model.local.ShipmentNetwork

@Dao
interface ShipmentNetworkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShipments(shipments: ShipmentNetwork)

    @Query("SELECT * FROM shipments")
    suspend fun getAllShipments(): List<ShipmentNetwork>

    @Update
    fun update(shipmentNetworkItem: ShipmentNetwork)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(shipments: List<ShipmentNetwork>)


}