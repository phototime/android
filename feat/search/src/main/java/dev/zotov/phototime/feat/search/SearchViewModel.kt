package dev.zotov.phototime.feat.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.zotov.phototime.feat.search.components.SearchTextField
import dev.zotov.phototime.state.blocs.CitiesForecastBloc
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SearchViewModel : ViewModel(), KoinComponent {

    // Blocs
    private val citiesForecastBloc: CitiesForecastBloc by inject()

    /** Current state of [SearchTextField] */
    val searchText = mutableStateOf("")

    /** Changes value of [SearchTextField] and calling [CitiesForecastBloc.search] when user stops typing */
    fun search(q: String) {
        // Changing value of SearchTextField
        searchText.value = q

        // creating a delayed action
        viewModelScope.launch {
            // waiting for a second
            delay(1000)

            // if in SearchTextField value has not changed in a second => user has stopped typing
            if (searchText.value == q) {
                citiesForecastBloc.search(q)
            }
        }
    }

    companion object {
        private const val searchTextKey = "search-text"
    }
}