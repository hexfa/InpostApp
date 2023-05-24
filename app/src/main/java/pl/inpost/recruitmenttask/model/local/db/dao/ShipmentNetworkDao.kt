package pl.inpost.recruitmenttask.model.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import pl.inpost.recruitmenttask.model.local.ShipmentNetwork

@Dao
interface ShipmentNetworkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(shipmentNetworkItem: ShipmentNetwork)

    @Update
    fun update(shipmentNetworkItem: ShipmentNetwork)

    @Delete
    fun delete(shipmentNetworkItem: ShipmentNetwork)

    @Query("SELECT * FROM ShipmentNetwork")
    fun getAllItems(): Flow<List<ShipmentNetwork>>

    @Insert
    fun insertItems(shipmentNetworkItem: List<ShipmentNetwork>)


}