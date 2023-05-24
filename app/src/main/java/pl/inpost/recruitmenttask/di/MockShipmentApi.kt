package pl.inpost.recruitmenttask.di

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.Single
import pl.inpost.recruitmenttask.R
import pl.inpost.recruitmenttask.model.api.ShipmentApi
import pl.inpost.recruitmenttask.model.local.CustomerNetwork
import pl.inpost.recruitmenttask.model.local.OperationsNetwork
import pl.inpost.recruitmenttask.model.local.ShipmentsResponse
import pl.inpost.recruitmenttask.utils.ApiType

class MockShipmentApi(
    @ApplicationContext private val context: Context,
    apiType: ApiType
) : ShipmentApi {

    private val response by lazy {
        val json = context.resources.openRawResource(R.raw.mock_shipment_api_response)
            .bufferedReader()
            .use { it.readText() }

        val jsonAdapter = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(apiType)
            .build()
            .adapter(ShipmentsResponse::class.java)

        jsonAdapter.fromJson(json) as ShipmentsResponse
    }


    private fun mockCustomerNetwork(
        email: String = "name@email.com",
        phoneNumber: String = "123 123 123",
        name: String = "Jan Kowalski"
    ) = CustomerNetwork(
        email = email,
        phoneNumber = phoneNumber,
        name = name
    )

    private fun mockOperationsNetwork(
        manualArchive: Boolean = false,
        delete: Boolean = false,
        collect: Boolean = false,
        highlight: Boolean = false,
        expandAvizo: Boolean = false,
        endOfWeekCollection: Boolean = false
    ) = OperationsNetwork(
        manualArchive = manualArchive,
        delete = delete,
        collect = collect,
        highlight = highlight,
        expandAvizo = expandAvizo,
        endOfWeekCollection = endOfWeekCollection
    )


    override fun getShipments(): Single<ShipmentsResponse> {
        val shipments = response
        return Single.just(shipments)
    }

}