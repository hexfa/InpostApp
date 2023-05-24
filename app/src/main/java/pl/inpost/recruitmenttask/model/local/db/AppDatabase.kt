package pl.inpost.recruitmenttask.model.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import pl.inpost.recruitmenttask.model.local.ShipmentNetwork
import pl.inpost.recruitmenttask.model.local.db.dao.ShipmentNetworkDao
import pl.inpost.recruitmenttask.utils.ApiType

@Database(
    entities = [ShipmentNetwork::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(TypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val shipmentNetworkItemDao: ShipmentNetworkDao

    companion object {

        private val DATABASE_NAME = "shipmentNetwork_database.db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        operator fun invoke(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build().also {
                    INSTANCE = it
                }
            }
    }


}