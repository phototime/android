package dev.zotov.phototime.feat.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.zotov.phototime.shared.R
import dev.zotov.phototime.shared.components.ActionTime
import dev.zotov.phototime.shared.theme.*
import dev.zotov.phototime.solarized.SunPhase

// TODO: move somewhere
interface IPhotoTime {
    val title: String
    val ballId: Int
}

sealed class PhotoTime : IPhotoTime {
    object FirstLight : PhotoTime() {
        override val title = "First Light"
        override val ballId = R.drawable.first_and_last_ball
    }

    object BlueHour : PhotoTime() {
        override val title = "Blue hour"
        override val ballId = R.drawable.blue_hour_ball
    }

    object GoldenHour : PhotoTime() {
        override val title = "Golden Hour"
        override val ballId = R.drawable.golden_hour_ball
    }

    object Sunrise : PhotoTime() {
        override val title = "Sunrise"
        override val ballId = R.drawable.sunrise_and_sunset_ball
    }

    object Day : PhotoTime() {
        override val title = "Day"
        override val ballId = R.drawable.day_ball
    }

    object Sunset : PhotoTime() {
        override val title = "Sunset"
        override val ballId = R.drawable.sunrise_and_sunset_ball
    }

    object LastLight : PhotoTime() {
        override val title = "Last Light"
        override val ballId = R.drawable.first_and_last_ball
    }

    companion object {
        fun fromSunPhase(sunPhase: SunPhase): PhotoTime {
            return when (sunPhase) {
                is SunPhase.FirstLight -> FirstLight
                is SunPhase.BlueHour -> BlueHour
                is SunPhase.GoldenHour -> GoldenHour
                is SunPhase.Sunrise -> Sunrise
                is SunPhase.Day -> Day
                is SunPhase.Sunset -> Sunset
                is SunPhase.LastLight -> LastLight
            }
        }
    }
}

@Composable
fun PhotoTimeTile(modifier: Modifier = Modifier, time: PhotoTime, actionTime: String) {
    Row(
        modifier = Modifier
            .composed { modifier }
            .padding(end = 25.dp, start = 25.dp, top = 15.dp)
            .clip(MaterialTheme.shapes.small)
            .background(TileColor)
            .padding(10.dp)
    ) {
        Circle(time.ballId)
        TileTitle(time.title)
        Spacer(modifier = Modifier.weight(1f))
        ActionTime(text = actionTime)
    }
}

@Composable
private fun Circle(id: Int) {
    val painter = painterResource(id = id)
    Image(painter = painter, contentDescription = null, modifier = Modifier.size(20.dp))
}

@Composable
private fun TileTitle(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(horizontal = 10.dp),
        style = MaterialTheme.typography.White16spNormal,
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = BackgroundPreviewColor)
private fun Preview() {
    PhototimeTheme {
        PhotoTimeTile(time = PhotoTime.FirstLight, actionTime = "6:29 AM – 6:39 AM")
    }
}