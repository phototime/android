package dev.zotov.phototime.core

import android.annotation.SuppressLint
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

internal fun provideWeatherRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.WEATHER_API_URL.toHttpUrl())
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

internal fun provideAlgoliaRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.ALGOLIA_API_URL.toHttpUrl())
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

internal fun provideOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient()
        .newBuilder()
        .addInterceptor(httpLoggingInterceptor)
        .apply {
            if (BuildConfig.DEBUG) ignoreAllSSLErrors()
        }
        .build()
}

/**
 * Should used only in debug mode!
 * Ignoring all SSL troubles
 */
fun OkHttpClient.Builder.ignoreAllSSLErrors(): OkHttpClient.Builder {
    val naiveTrustManager = @SuppressLint("CustomX509TrustManager")
    object : X509TrustManager {
        override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
        override fun checkClientTrusted(certs: Array<X509Certificate>, authType: String) = Unit
        override fun checkServerTrusted(certs: Array<X509Certificate>, authType: String) = Unit
    }

    val insecureSocketFactory = SSLContext.getInstance("TLSv1.2").apply {
        val trustAllCerts = arrayOf<TrustManager>(naiveTrustManager)
        init(null, trustAllCerts, SecureRandom())
    }.socketFactory

    sslSocketFactory(insecureSocketFactory, naiveTrustManager)
    hostnameVerifier { _, _ -> true }
    return this
}

internal fun provideWeatherApi(okHttpClient: OkHttpClient): WeatherApi {
    val retrofit = provideWeatherRetrofit(okHttpClient)
    return retrofit.create(WeatherApi::class.java)
}

internal fun provideAlgoliaApi(okHttpClient: OkHttpClient): AlgoliaApi {
    val retrofit = provideAlgoliaRetrofit(okHttpClient)
    return retrofit.create(AlgoliaApi::class.java)
}