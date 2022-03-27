package dev.zotov.phototime.shared.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.zotov.phototime.shared.theme.BackgroundPreviewColor
import dev.zotov.phototime.shared.theme.PhototimeTheme
import dev.zotov.phototime.shared.theme.Grey16spNormal
import dev.zotov.phototime.shared.theme.timeDurationAbbreviation

@Composable
fun ActionTime(text: String) {

    val intervalIndexes = mutableListOf<Pair<Int, Int>>()
    var i = 0
    while (i < text.length) {
        if (text[i] == 'P' || text[i] == 'A') {
            intervalIndexes.add(Pair(i, i + 2))
            i++
        }
        i++
    }


    Text(text = buildAnnotatedString {
        append(text)
        addStyle(
            style = MaterialTheme.typography.Grey16spNormal.toSpanStyle(),
            start = 0,
            end = text.length
        )

        intervalIndexes.forEach {
            addStyle(
                style = MaterialTheme.typography.timeDurationAbbreviation.toSpanStyle(),
                start = it.first,
                end = it.second
            )
        }
    })
}

@Composable
@Preview(showBackground = true, backgroundColor = BackgroundPreviewColor)
private fun PreviewDoubleTime() {
    PhototimeTheme {
        Box(modifier = Modifier.padding(20.dp)) {
            ActionTime(text = "6:29 AM – 6:39 AM")
        }
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = BackgroundPreviewColor)
private fun PreviewOneTime() {
    PhototimeTheme {
        Box(modifier = Modifier.padding(20.dp)) {
            ActionTime(text = "6:27 AM")
        }
    }
}