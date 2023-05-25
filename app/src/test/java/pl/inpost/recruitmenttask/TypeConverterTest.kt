package pl.inpost.recruitmenttask

import com.google.gson.Gson
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import pl.inpost.recruitmenttask.model.local.CustomerNetwork
import pl.inpost.recruitmenttask.model.local.EventLogNetwork
import pl.inpost.recruitmenttask.model.local.OperationsNetwork
import pl.inpost.recruitmenttask.model.local.db.TypeConverter
import pl.inpost.recruitmenttask.utils.ApiType
import java.time.ZonedDateTime

class TypeConverterTest {
    private lateinit var gson: Gson
    private lateinit var typeConverter: TypeConverter

    @Before
    fun setup(){
        gson=Gson()
        typeConverter=TypeConverter()
    }

    @Test
    fun testFromStringEventLogNetwork(){
        val apiType = ApiType()
        val eventLogNetworkListJson=" [\n" +
                "        {\n" +
                "          \"name\": \"READY_TO_PICKUP\",\n" +
                "          \"date\": \"2018-08-01T04:56:07Z\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"SENT_FROM_SOURCE_BRANCH\",\n" +
                "          \"date\": \"2018-08-18T04:56:07Z\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"CONFIRMED\",\n" +
                "          \"date\": \"2018-08-14T04:56:07Z\"\n" +
                "        }\n" +
                "      ]"
        val eventLogNetworks= arrayListOf(EventLogNetwork("READY_TO_PICKUP",
            apiType.toZonedDateTime("2018-08-01T04:56:07Z")!!),
            EventLogNetwork("SENT_FROM_SOURCE_BRANCH",
                apiType.toZonedDateTime("2018-08-18T04:56:07Z")!!),
            EventLogNetwork("CONFIRMED",
                apiType.toZonedDateTime("2018-08-14T04:56:07Z")!!)
        )

        Assert.assertEquals(eventLogNetworks,typeConverter.fromStringEventLogNetwork(eventLogNetworkListJson))
    }

    @Test
    fun testToStringEventLogNetwork(){
        val apiType = ApiType()
        val eventLogNetworkListJson=" [\n" +
                "        {\n" +
                "          \"name\": \"READY_TO_PICKUP\",\n" +
                "          \"date\": \"2018-08-01T04:56:07Z\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"SENT_FROM_SOURCE_BRANCH\",\n" +
                "          \"date\": \"2018-08-18T04:56:07Z\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"CONFIRMED\",\n" +
                "          \"date\": \"2018-08-14T04:56:07Z\"\n" +
                "        }\n" +
                "      ]"
        val eventLogNetworks= arrayListOf(EventLogNetwork("READY_TO_PICKUP",
            ZonedDateTime.parse("2018-08-01T04:56:07Z")),
            EventLogNetwork("SENT_FROM_SOURCE_BRANCH",
                ZonedDateTime.parse("2018-08-18T04:56:07Z")),
            EventLogNetwork("CONFIRMED",
                ZonedDateTime.parse("2018-08-14T04:56:07Z"))
        )

        Assert.assertEquals(eventLogNetworkListJson,typeConverter.toString(eventLogNetworks))
    }

    @Test
    fun testFromStringCustomerNetwork(){
        val customerNetworkJson="{\n" +
                "        \"email\": \"sender@example.com\",\n" +
                "        \"phoneNumber\": \"500500500\",\n" +
                "        \"name\": \"sender\"\n" +
                "      }"
        val customerNetwork=
            CustomerNetwork("sender@example.com","500500500","sender")

        Assert.assertEquals(customerNetwork,typeConverter.fromStringCustomerNetwork(customerNetworkJson))
    }

    @Test
    fun testToStringCustomerNetwork(){
        val customerNetworkJson="{\n" +
                "        \"email\": \"sender@example.com\",\n" +
                "        \"phoneNumber\": \"500500500\",\n" +
                "        \"name\": \"sender\"\n" +
                "      }"
        val customerNetwork=
            CustomerNetwork("sender@example.com","500500500","sender")

        Assert.assertEquals(customerNetworkJson,typeConverter.toString(customerNetwork))
    }

    @Test
    fun testFromStringOperationsNetwork(){
        val operationsNetworkJson=" {\n" +
                "        \"delete\": true,\n" +
                "        \"manualArchive\": true,\n" +
                "        \"collect\": true,\n" +
                "        \"highlight\": true,\n" +
                "        \"expandAvizo\": true,\n" +
                "        \"endOfWeekCollection\": false\n" +
                "      }"
        val operationsNetwork= OperationsNetwork(true,true,
            true,true,true,false)

        Assert.assertEquals(operationsNetwork,typeConverter.fromString(operationsNetworkJson))
    }

    @Test
    fun testToStringOperationsNetwork(){
        val operationsNetworkJson=" {\n" +
                "        \"delete\": true,\n" +
                "        \"manualArchive\": true,\n" +
                "        \"collect\": true,\n" +
                "        \"highlight\": true,\n" +
                "        \"expandAvizo\": true,\n" +
                "        \"endOfWeekCollection\": false\n" +
                "      }"
        val operationsNetwork= OperationsNetwork(true,true,
            true,true,true,false)

        Assert.assertEquals(operationsNetworkJson,typeConverter.toString(operationsNetwork))
    }
}