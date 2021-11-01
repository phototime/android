package dev.zotov.phototime.feat.home

import androidx.lifecycle.ViewModel
import dev.zotov.phototime.shared.usecases.FetchForecastUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.java.KoinJavaComponent.inject

class HomeViewModel(private val fetchForecastUseCase: FetchForecastUseCase): ViewModel() {

    /** Current state of [HomeScreen] */
    private val _state = MutableStateFlow<HomeState>(HomeState.Loading)
    val state: StateFlow<HomeState> get() = _state.asStateFlow()


    init {

    }
}