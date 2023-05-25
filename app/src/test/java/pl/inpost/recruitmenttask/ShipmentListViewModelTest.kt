package pl.inpost.recruitmenttask

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import pl.inpost.recruitmenttask.model.api.ShipmentApi
import pl.inpost.recruitmenttask.model.local.*
import pl.inpost.recruitmenttask.model.local.db.dao.ShipmentNetworkDao
import pl.inpost.recruitmenttask.model.repository.ShipmentRepository
import pl.inpost.recruitmenttask.model.repository.ShipmentRepositoryDao
import pl.inpost.recruitmenttask.viewmodel.ShipmentListViewModel
import java.time.ZonedDateTime
import kotlin.random.Random

class ShipmentListViewModelTest {

    private lateinit var mockShipmentApi: ShipmentApi
    private lateinit var mockShipmentDao: ShipmentNetworkDao
    private lateinit var shipmentListViewModel: ShipmentListViewModel

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {
        mockShipmentApi = Mockito.mock(ShipmentApi::class.java)
        mockShipmentDao = Mockito.mock(ShipmentNetworkDao::class.java)

        shipmentListViewModel = ShipmentListViewModel(
            ShipmentRepository(mockShipmentApi),
            ShipmentRepositoryDao(mockShipmentDao)
        )
    }


    @Test
    fun test_getShipment() = runBlocking {
        val shipmentsResponse = ShipmentsResponse(arrayListOf(mockShipmentNetwork()))
        Mockito.`when`(mockShipmentApi.getShipments()).thenReturn(shipmentsResponse)

        val shipmentsResponseLiveData = MutableLiveData<ShipmentsResponse>()

        shipmentsResponseLiveData.postValue(shipmentsResponse)

        //Act
        shipmentListViewModel.getShipments()

        Assert.assertEquals(
            shipmentsResponseLiveData.value,
            shipmentListViewModel.shipmentNetworksRemoteLiveData.value
        )
    }

    @Test
    fun test_addItems() {
        val shipmentsResponse = ShipmentsResponse(arrayListOf(mockShipmentNetwork()))
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