package dev.zotov.phototime.core.usecases

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import dev.zotov.phototime.core.proto.SunPhaseListProto
import dev.zotov.phototime.shared.createLogger
import dev.zotov.phototime.shared.usecases.UseCachedSunPhasesUseCase
import dev.zotov.phototime.shared.utils.DataStores
import dev.zotov.phototime.solarized.SunPhase
import dev.zotov.phototime.solarized.SunPhaseList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.InputStream
import java.io.OutputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private val Context.dataStore: DataStore<SunPhaseListProto> by dataStore(
    fileName = DataStores.sunPhases,
    serializer = SunPhaseSerializer,
)

class UseCachedSunPhasesUseCaseImpl(private val context: Context): UseCachedSunPhasesUseCase {
    private val logger = createLogger("UseCachedSunPhasesUseCase")

    override suspend fun save(sunPhaseList: SunPhaseList) {
        val isoFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

        val list = listOf<SunPhaseListProto.SunPhaseProto>(
            SunPhaseListProto.SunPhaseProto.newBuilder()
                .setType(SunPhaseListProto.SunPhaseType.FirstLight)
                .setFrom(isoFormatter.format(sunPhaseList.firstLight.date))
                .setTo(isoFormatter.format(sunPhaseList.firstLight.date))
                .build(),
            SunPhaseListProto.SunPhaseProto.newBuilder()
                .setType(SunPhaseListProto.SunPhaseType.MorningBlueHour)
                .setFrom(isoFormatter.format(sunPhaseList.morningBlueHour.start))
                .setTo(isoFormatter.format(sunPhaseList.morningGoldenHour.end))
                .build(),
            SunPhaseListProto.SunPhaseProto.newBuilder()
                .setType(SunPhaseListProto.SunPhaseType.Sunrise)
                .setFrom(isoFormatter.format(sunPhaseList.sunrise.date))
                .setTo(isoFormatter.format(sunPhaseList.sunrise.date))
                .build(),
            SunPhaseListProto.SunPhaseProto.newBuilder()
                .setType(SunPhaseListProto.SunPhaseType.MorningGoldenHour)
                .setFrom(isoFormatter.format(sunPhaseList.morningGoldenHour.start))
                .setTo(isoFormatter.format(sunPhaseList.morningGoldenHour.end))
                .build(),
            SunPhaseListProto.SunPhaseProto.newBuilder()
                .setType(SunPhaseListProto.SunPhaseType.Day)
                .setFrom(isoFormatter.format(sunPhaseList.day.start))
                .setTo(isoFormatter.format(sunPhaseList.day.end))
                .build(),
            SunPhaseListProto.SunPhaseProto.newBuilder()
                .setType(SunPhaseListProto.SunPhaseType.EveningGoldenHour)
                .setFrom(isoFormatter.format(sunPhaseList.eveningGoldenHour.start))
                .setTo(isoFormatter.format(sunPhaseList.eveningGoldenHour.end))
                .build(),
            SunPhaseListProto.SunPhaseProto.newBuilder()
                .setType(SunPhaseListProto.SunPhaseType.Sunset)
                .setFrom(isoFormatter.format(sunPhaseList.sunset.date))
                .setTo(isoFormatter.format(sunPhaseList.sunset.date))
                .build(),
            SunPhaseListProto.SunPhaseProto.newBuilder()
                .setType(SunPhaseListProto.SunPhaseType.EveningBlueHour)
                .setFrom(isoFormatter.format(sunPhaseList.eveningBlueHour.start))
                .setTo(isoFormatter.format(sunPhaseList.eveningBlueHour.end))
                .build(),
            SunPhaseListProto.SunPhaseProto.newBuilder()
                .setType(SunPhaseListProto.SunPhaseType.LastLight)
                .setFrom(isoFormatter.format(sunPhaseList.lastLight.date))
                .setTo(isoFormatter.format(sunPhaseList.lastLight.date))
                .build(),
        )

        logger.info { "cache $list" }

        context.dataStore.updateData {
            it.toBuilder()
                .clearList()
                .addAllList(list)
                .build()
        }
    }

    override suspend fun get(): Flow<SunPhaseList?> = context.dataStore.data.map {
        if (it.listList.size < 9) return@map null

        SunPhaseList(
            firstLight = SunPhase.FirstLight(
                date = LocalDateTime.parse(it.listList[0].from)
            ),
            morningBlueHour = SunPhase.BlueHour(
                start = LocalDateTime.parse(it.listList[1].from),
                end = LocalDateTime.parse(it.listList[1].to),
            ),
            sunrise = SunPhase.Sunrise(
                date = LocalDateTime.parse(it.listList[2].from)
            ),
            morningGoldenHour = SunPhase.GoldenHour(
                start = LocalDateTime.parse(it.listList[3].from),
                end = LocalDateTime.parse(it.listList[3].to),
            ),
            day = SunPhase.Day(
                start = LocalDateTime.parse(it.listList[4].from),
                end = LocalDateTime.parse(it.listList[4].to),
            ),
            eveningGoldenHour = SunPhase.GoldenHour(
                start = LocalDateTime.parse(it.listList[5].from),
                end = LocalDateTime.parse(it.listList[5].to),
            ),
            sunset = SunPhase.Sunset(
                date = LocalDateTime.parse(it.listList[6].from)
            ),
            eveningBlueHour = SunPhase.BlueHour(
                start = LocalDateTime.parse(it.listList[7].from),
                end = LocalDateTime.parse(it.listList[7].to),
            ),
            lastLight = SunPhase.LastLight(
                date = LocalDateTime.parse(it.listList[8].from)
            ),
        )
    }
}


object SunPhaseSerializer: Serializer<SunPhaseListProto> {
    override val defaultValue: SunPhaseListProto
        get() = SunPhaseListProto.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): SunPhaseListProto {
        return SunPhaseListProto.parseFrom(input)
    }

    override suspend fun writeTo(t: SunPhaseListProto, output: OutputStream) {
        t.writeTo(output)
    }
}
