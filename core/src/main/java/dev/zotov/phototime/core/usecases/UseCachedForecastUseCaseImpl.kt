package dev.zotov.phototime.core.usecases

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import dev.zotov.phototime.core.proto.ForecastProto
import dev.zotov.phototime.shared.models.Forecast
import dev.zotov.phototime.shared.models.HourlyForecast
import dev.zotov.phototime.shared.usecases.UseCachedForecastUseCase
import dev.zotov.phototime.shared.utils.DataStores
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.InputStream
import java.io.OutputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private val Context.dataStore: DataStore<ForecastProto> by dataStore(
    fileName = DataStores.forecast,
    serializer = ForecastSerializer,
)

class UseCachedForecastUseCaseImpl(private val context: Context): UseCachedForecastUseCase {

    override suspend fun save(forecast: Forecast) {
        val hourly = forecast.hourly.map {
            ForecastProto.HourlyForecastProto.newBuilder()
                .setIcon(it.icon)
                .setTime(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(it.time))
                .setTemp(it.temp)
                .build()
        }

        context.dataStore.updateData {
            it.toBuilder()
                .setIcon(forecast.icon)
                .setTemp(forecast.temp)
                .setWind(forecast.wind)
                .setHumidity(forecast.humidity)
                .clearHourly()
                .addAllHourly(hourly)
                .build()
        }
    }

    override suspend fun get(): Flow<Forecast?> = context.dataStore.data.map {
        Forecast(
            icon = it.icon,
            temp = it.temp,
            wind = it.wind,
            humidity = it.humidity,
            hourly = it.hourlyList.map { hourly ->
                HourlyForecast(
                    icon = hourly.icon,
                    time = LocalDateTime.parse(hourly.time),
                    temp = hourly.temp
                )
            }
        )
    }
}


object ForecastSerializer: Serializer<ForecastProto> {
    override val defaultValue: ForecastProto
        get() = ForecastProto.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): ForecastProto {
        return ForecastProto.parseFrom(input)
    }

    override suspend fun writeTo(t: ForecastProto, output: OutputStream) {
        t.writeTo(output)
    }
}
