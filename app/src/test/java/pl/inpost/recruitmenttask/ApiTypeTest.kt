package pl.inpost.recruitmenttask

import org.junit.Assert.assertNotNull
import org.junit.Test
import pl.inpost.recruitmenttask.utils.ApiType
import java.time.ZonedDateTime

class ApiTypeTest {

    @Test
    fun testToZonedDateTime() {
        val apiType = ApiType()
        val inputValue = "2023-05-24T10:30:00+02:00" // Replace with your test data

        val result = apiType.toZonedDateTime(inputValue)

        assertNotNull(result)
        // Add more assertions as needed
    }

    @Test
    fun testFromZonedDateTime() {
        val apiType = ApiType()
        val inputDate = ZonedDateTime.now() // Replace with your test data

        val result = apiType.fromZonedDateTime(inputDate)

        assertNotNull(result)
        // Add more assertions as needed
    }
}