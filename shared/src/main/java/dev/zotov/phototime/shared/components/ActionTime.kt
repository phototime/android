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
import dev.zotov.phototime.shared.theme.timeDuration
import dev.zotov.phototime.shared.theme.timeDurationAbbreviation

@Composable
fun ActionTime(text: String) {
    Text(text = buildAnnotatedString {
        append(text)
        addStyle(
            style = MaterialTheme.typography.timeDuration.toSpanStyle(),
            start = 0,
            end = 4
        )
        addStyle(
            style = MaterialTheme.typography.timeDurationAbbreviation.toSpanStyle(),
            start = 4,
            end = 7
        )
        if (text.length > 7) {
            addStyle(
                style = MaterialTheme.typography.timeDuration.toSpanStyle(),
                start = 7,
                end = 14
            )
            addStyle(
                style = MaterialTheme.typography.timeDurationAbbreviation.toSpanStyle(),
                start = 14,
                end = 17
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