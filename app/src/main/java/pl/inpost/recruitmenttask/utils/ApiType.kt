package pl.inpost.recruitmenttask.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import dagger.Reusable
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@Reusable
class ApiType @Inject constructor() {

    @RequiresApi(Build.VERSION_CODES.O)
    private val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

    @RequiresApi(Build.VERSION_CODES.O)
    @FromJson
    fun toZonedDateTime(value: String?): ZonedDateTime? =
        formatter.parse(value, ZonedDateTime::from)

    @RequiresApi(Build.VERSION_CODES.O)
    @ToJson
    fun fromZonedDateTime(date: ZonedDateTime?): String? = date?.format(formatter)


}
