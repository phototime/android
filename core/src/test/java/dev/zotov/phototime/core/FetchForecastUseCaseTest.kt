package dev.zotov.phototime.core

import dev.zotov.phototime.core.usecases.FetchForecastUseCaseImpl
import dev.zotov.phototime.shared.models.Forecast
import dev.zotov.phototime.shared.usecases.FetchForecastUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.logger.Level
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import java.net.HttpURLConnection
import kotlin.test.assertEquals

class FetchForecastUseCaseTest: KoinTest {

    private val mockWebServer = MockWebServer()
    private val fetchForecastUseCase: FetchForecastUseCase by inject()

    private val testModule = module {
        single { provideOkHttpClient() }
        single { provideWeatherApi(get()) }
        single { provideRetrofit(get(), mockWebServer.url("/")) }
        single<FetchForecastUseCase> { FetchForecastUseCaseImpl(get()) }
    }

    @get:Rule
    val koinRule = KoinTestRule.create {
        printLogger(Level.DEBUG)
        modules(testModule)
    }

    @Before
    fun init() {
        mockWebServer.start()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should fetch`() = runBlocking {
        // Arrange
        val mockResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(successForecastApiResponse)
        mockWebServer.enqueue(mockResponse)

        // Act
        val result = fetchForecastUseCase.execute("perm")

        // Assert
        assertEquals(result.isSuccess, true)
        assertEquals(result.getOrNull(), successForecastApiDomainObject)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}