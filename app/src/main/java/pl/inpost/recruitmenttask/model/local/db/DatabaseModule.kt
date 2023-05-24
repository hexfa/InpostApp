package pl.inpost.recruitmenttask.model.local.db

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pl.inpost.recruitmenttask.model.local.db.dao.ShipmentNetworkDao
import pl.inpost.recruitmenttask.model.repository.ShipmentRepositoryDao

@Module
@InstallIn(
    SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideShipmentNetworkItemDao(database: AppDatabase): ShipmentNetworkDao {
        return database.shipmentNetworkItemDao
    }

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase(context)
    }

    @Provides
    fun provideRepository(dao: ShipmentNetworkDao): ShipmentRepositoryDao {
        return ShipmentRepositoryDao(dao)
    }
}