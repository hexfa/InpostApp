package pl.inpost.recruitmenttask.model.local.db

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import pl.inpost.recruitmenttask.model.local.CustomerNetwork
import pl.inpost.recruitmenttask.model.local.EventLogNetwork
import pl.inpost.recruitmenttask.model.local.OperationsNetwork
import java.lang.reflect.Type
import java.time.ZonedDateTime

class TypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromStringEventLogNetwork(value: String?): List<EventLogNetwork> {
        if (value == null) {
            return emptyList()
        }

        val listType: Type = object : TypeToken<List<EventLogNetwork>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun toString(list: List<EventLogNetwork>): String {
        return gson.toJson(list)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromStringZonedDateTime(value: String?): ZonedDateTime? {
        return if (value == null) {
            null
        } else {
            ZonedDateTime.parse(value)
        }
    }

    @TypeConverter
    fun toString(value: ZonedDateTime?): String? {
        return value?.toString()
    }

    @TypeConverter
    fun fromStringCustomerNetwork(value: String?): CustomerNetwork? {
        return if (value == null) {
            null
        } else {
            gson.fromJson(value, CustomerNetwork::class.java)
        }
    }

    @TypeConverter
    fun toString(value: CustomerNetwork?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun fromString(value: String?): OperationsNetwork? {
        return if (value == null) {
            null
        } else {
            gson.fromJson(value, OperationsNetwork::class.java)
        }
    }

    @TypeConverter
    fun toString(value: OperationsNetwork?): String? {
        return gson.toJson(value)
    }
}