package pl.inpost.recruitmenttask.model.local.db
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import dagger.Reusable
import org.json.JSONArray
import org.json.JSONObject
import pl.inpost.recruitmenttask.model.local.CustomerNetwork
import pl.inpost.recruitmenttask.model.local.EventLogNetwork
import pl.inpost.recruitmenttask.model.local.OperationsNetwork
import java.time.ZonedDateTime


@Reusable
    class TypeConverter {

        @TypeConverter
        @RequiresApi(Build.VERSION_CODES.O)
        fun toEventLog(value: String): List<EventLogNetwork>? {
            val jsonArray = JSONArray(value)
            val eventLogs = mutableListOf<EventLogNetwork>()
            for (i in 0 until jsonArray.length()) {
                val json = jsonArray.getJSONObject(i)
                val name = json.getString("name")
                val date = ZonedDateTime.parse(json.getString("date"))
                eventLogs.add(EventLogNetwork(name, date))
            }
            return eventLogs
        }

        @TypeConverter
        fun fromEventLog(eventLogs: List<EventLogNetwork>?): String? {
            val jsonArray = JSONArray()
            eventLogs?.forEach { eventLog ->
                val json = JSONObject()
                json.put("name", eventLog.name)
                json.put("date", eventLog.date.toString())
                jsonArray.put(json)
            }
            return jsonArray.toString()
        }

        @TypeConverter
        @RequiresApi(Build.VERSION_CODES.O)
        fun toExpiryDate(value: String): ZonedDateTime {
            return ZonedDateTime.parse(value)
        }

        @TypeConverter
        @RequiresApi(Build.VERSION_CODES.O)
        fun fromExpiryDate(value: ZonedDateTime): String {
            return value.toString()
        }

        @TypeConverter
        @RequiresApi(Build.VERSION_CODES.O)
        fun toCustomerNetwork(value: String): CustomerNetwork {
            val json = JSONObject(value)
            return CustomerNetwork(
                json.getString("email"),
                json.getString("phoneNumber"),
                json.getString("name")
            )
        }

        @TypeConverter
        @RequiresApi(Build.VERSION_CODES.O)
        fun fromCustomerNetwork(value: CustomerNetwork): String {
            return JSONObject().apply {
                put("email", value.email)
                put("phoneNumber", value.phoneNumber)
                put("name", value.name)
            }.toString()
        }

        @TypeConverter
        @RequiresApi(Build.VERSION_CODES.O)
        fun toOperationsNetwork(value: String): OperationsNetwork {
            val json = JSONObject(value)
            return OperationsNetwork(
                json.getBoolean("manualArchive"),
                json.getBoolean("delete"),
                json.getBoolean("collect"),
                json.getBoolean("highlight"),
                json.getBoolean("expandAvizo"),
                json.getBoolean("endOfWeekCollection")
            )
        }

        @TypeConverter
        @RequiresApi(Build.VERSION_CODES.O)
        fun fromOperationsNetwork(value: OperationsNetwork): String {
            return JSONObject().apply {
                put("manualArchive", value.manualArchive)
                put("delete", value.delete)
                put("collect", value.collect)
                put("highlight", value.highlight)
                put("expandAvizo", value.expandAvizo)
                put("endOfWeekCollection", value.endOfWeekCollection)
            }.toString()
        }
    }

