package pl.inpost.recruitmenttask

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import pl.inpost.recruitmenttask.model.api.ShipmentApi
import pl.inpost.recruitmenttask.model.local.*
import pl.inpost.recruitmenttask.model.repository.ShipmentRepository
import java.time.ZonedDateTime
import kotlin.random.Random

class ShipmentRepositoryTest {
    lateinit var mockShipmentApi: ShipmentApi

    @Before
    fun setup(){
        mockShipmentApi= Mockito.mock(ShipmentApi::class.java)
    }

    @Test
     fun test_getShipments() = runBlocking{
        val shipmentRepository=ShipmentRepository(mockShipmentApi)
        val shipmentsResponse=ShipmentsResponse(arrayListOf(mockShipmentNetwork()))

        Mockito.`when`(mockShipmentApi.getShipments()).thenReturn(shipmentsResponse)
        val shipmentsResponseResult=shipmentRepository.getShipments()
        Assert.assertNotNull(shipmentsResponseResult);
        Assert.assertEquals(mockShipmentApi.getShipments(), shipmentsResponseResult);
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