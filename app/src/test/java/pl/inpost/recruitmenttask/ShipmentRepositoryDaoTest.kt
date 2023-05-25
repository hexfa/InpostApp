package pl.inpost.recruitmenttask

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.internal.verification.VerificationModeFactory.times
import pl.inpost.recruitmenttask.di.MockShipmentApi
import pl.inpost.recruitmenttask.model.local.*
import pl.inpost.recruitmenttask.model.local.db.dao.ShipmentNetworkDao
import pl.inpost.recruitmenttask.model.repository.ShipmentRepositoryDao
import java.time.ZonedDateTime
import kotlin.random.Random

class ShipmentRepositoryDaoTest {
    lateinit var mockShipmentNetworkDao: ShipmentNetworkDao
    lateinit var shipmentRepositoryDao: ShipmentRepositoryDao

    @Before
    fun setup(){
        mockShipmentNetworkDao=Mockito.mock(ShipmentNetworkDao::class.java)
        shipmentRepositoryDao= ShipmentRepositoryDao(mockShipmentNetworkDao)
    }

    @Test
    fun test_getAllItem() = runBlocking{
        val shipmentNetworkList= arrayListOf<ShipmentNetwork>()
        shipmentNetworkList.add(mockShipmentNetwork())
        shipmentNetworkList.add(mockShipmentNetwork())
        shipmentNetworkList.add(mockShipmentNetwork())
        shipmentNetworkList.add(mockShipmentNetwork())

        Mockito.`when`(mockShipmentNetworkDao.getAllShipments()).thenReturn(shipmentNetworkList)

        Assert.assertEquals(shipmentNetworkList,shipmentRepositoryDao.getAllItem())
    }

    @Test
    fun testUpdateItem() {
        // Mocked input data
        val shipmentItem = mockShipmentNetwork()
        Mockito.doNothing().`when`(mockShipmentNetworkDao).update(shipmentItem)

        // Call the method under test
        shipmentRepositoryDao.updateItem(shipmentItem)

        // Verify the expected interaction with the DAO
        Mockito.verify(mockShipmentNetworkDao,times(1)).update(shipmentItem)
    }

    @Test
    fun testInsertItem() = runBlocking{
        // Mocked input data
        val shipmentItem = mockShipmentNetwork()
        // Call the method under test
        shipmentRepositoryDao.insert(shipmentItem)

        // Verify the expected interaction with the DAO
        Mockito.verify(mockShipmentNetworkDao, times(1)).insertShipments(shipmentItem)
    }

    @Test
    fun testInsertItems() = runBlocking{
        // Mocked input data
        val shipmentsResponse = ShipmentsResponse(arrayListOf(mockShipmentNetwork()))

        // Call the method under test
        shipmentRepositoryDao.insertItems(shipmentsResponse)

        // Verify the expected interaction with the DAO
        Mockito.verify(mockShipmentNetworkDao, times(1)).
        insertItems(shipmentsResponse.shipments)
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