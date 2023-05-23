package pl.inpost.recruitmenttask.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pl.inpost.recruitmenttask.model.api.ShipmentApi

@InstallIn(SingletonComponent::class)
@Module
class NetworkAndroidModule {

    @Provides
    fun shipmentApi(@ApplicationContext context: Context, apiType: ApiType): ShipmentApi = MockShipmentApi(context, apiType)
}
