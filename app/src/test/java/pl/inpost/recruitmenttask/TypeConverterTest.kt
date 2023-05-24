package pl.inpost.recruitmenttask

import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import pl.inpost.recruitmenttask.model.local.EventLogNetwork
import pl.inpost.recruitmenttask.model.local.db.TypeConverter
import java.lang.reflect.Type
import java.time.ZonedDateTime

class TypeConverterTest {
    lateinit var gson: Gson
    lateinit var typeConverter: TypeConverter

    @Before
    fun setup(){
        gson=Gson()
        typeConverter=TypeConverter()
    }

    @Test
    fun testFromStringEventLogNetwork(){
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

        Assert.assertEquals(eventLogNetworks,typeConverter.fromStringEventLogNetwork(eventLogNetworkListJson))
    }
}