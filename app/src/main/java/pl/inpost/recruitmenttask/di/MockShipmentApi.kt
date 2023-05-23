package pl.inpost.recruitmenttask.di

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import pl.inpost.recruitmenttask.R
import pl.inpost.recruitmenttask.view.adapter.ApiType
import pl.inpost.recruitmenttask.model.api.ShipmentApi
import pl.inpost.recruitmenttask.model.CustomerNetwork
import pl.inpost.recruitmenttask.model.EventLogNetwork
import pl.inpost.recruitmenttask.model.OperationsNetwork
import pl.inpost.recruitmenttask.model.ShipmentNetwork
import pl.inpost.recruitmenttask.model.ShipmentStatus
import pl.inpost.recruitmenttask.model.ShipmentType
import pl.inpost.recruitmenttask.model.ShipmentsResponse
import java.time.ZonedDateTime
import kotlin.random.Random

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
    private var firstUse = true

    override suspend fun getShipments(): List<ShipmentNetwork> {
        delay(1000)
        return if (firstUse) {
            firstUse = false
            emptyList()
        } else {
            response.shipments
        }
    }

    private fun mockShipmentNetwork(
        number: String = Random.nextLong(1, 9999_9999_9999_9999).toString(),
        type: ShipmentType = ShipmentType.PARCEL_LOCKER,
        status: ShipmentStatus = ShipmentStatus.DELIVERED,
        sender: CustomerNetwork? = mockCustomerNetwork(),
        receiver: CustomerNetwork? = mockCustomerNetwork(),
        operations: OperationsNetwork = mockOperationsNetwork(),
        eventLog: List<EventLogNetwork> = emptyList(),
        openCode: String? = null,
        expireDate: ZonedDateTime? = null,
        storedDate: ZonedDateTime? = null,
        pickupDate: ZonedDateTime? = null
    ) = ShipmentNetwork(
        number = number,
        shipmentType = type.name,
        status = status.name,
        eventLog = eventLog,
        openCode = openCode,
        expiryDate = expireDate,
        storedDate = storedDate,
        pickUpDate = pickupDate,
        receiver = receiver,
        sender = sender,
        operations = operations
    )

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
}