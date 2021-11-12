package dev.zotov.phototime.core.usecases

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import dev.zotov.phototime.core.proto.ForecastProto
import dev.zotov.phototime.domain.ForecastType
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
                .setType(ForecastProto.ForecastType.valueOf(it.type.ordinal))
                .setTime(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(it.time))
                .setTemp(it.temp)
                .build()
        }

        Log.d("save", ForecastProto.ForecastType.values()[forecast.type.ordinal].toString())

        context.dataStore.updateData {
            it.toBuilder()
                .setType(ForecastProto.ForecastType.values()[forecast.type.ordinal])
                .setTemp(forecast.temp)
                .setWind(forecast.wind)
                .setHumidity(forecast.humidity)
                .clearHourly()
                .addAllHourly(hourly)
                .build()
        }
    }

    override suspend fun get(): Flow<Forecast?> = context.dataStore.data.map {

        Log.d("get", ForecastType.valueOf(it.type.toString()).toString())

        Forecast(
            type = ForecastType.valueOf(it.type.toString()),
            temp = it.temp,
            wind = it.wind,
            humidity = it.humidity,
            hourly = it.hourlyList.map { hourly ->
                HourlyForecast(
                    type = ForecastType.valueOf(hourly.type.toString()),
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
