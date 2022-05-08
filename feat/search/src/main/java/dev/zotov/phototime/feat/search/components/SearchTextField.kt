package dev.zotov.phototime.feat.search.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.zotov.phototime.feat.search.SearchViewModel
import dev.zotov.phototime.shared.R
import dev.zotov.phototime.shared.theme.BackgroundPreviewColor
import dev.zotov.phototime.shared.theme.Grey16spNormal
import dev.zotov.phototime.shared.theme.PhototimeTheme
import dev.zotov.phototime.shared.theme.White16spNormal
import dev.zotov.phototime.state.Store
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get
import org.koin.androidx.compose.get

@Composable
fun SearchTextField() {
    val viewModel = get<SearchViewModel>()

    TextField(
        value = viewModel.searchText.value,
        onValueChange = { viewModel.search(it) },
        modifier = Modifier
            .padding(horizontal = 25.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(13.dp),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        singleLine = true,
        leadingIcon = { SearchIcon() },
        placeholder = { Text(text = "Search", style = MaterialTheme.typography.Grey16spNormal) },
        textStyle = MaterialTheme.typography.White16spNormal,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color(0xFFFFFFFF).copy(alpha = 0.05F),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            cursorColor = Color(0xFFFFFFFF).copy(alpha = 0.5F),
            textColor = Color(0xFFFFFFFF),
        )
    )
}

@Composable
private fun SearchIcon() {
    val painter = painterResource(id = R.drawable.search)
    Image(
        painter = painter,
        contentDescription = "search icon",
        modifier = Modifier.size(24.dp),
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = BackgroundPreviewColor)
private fun Preview() {
    PhototimeTheme {
        SearchTextField()
    }
}