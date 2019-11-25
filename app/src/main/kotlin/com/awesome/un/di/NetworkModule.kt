package com.awesome.un.di

import android.content.Context
import com.awesome.un.BuildConfig
import com.awesome.un.data.api.APIClient
import com.readystatesoftware.chuck.ChuckInterceptor
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.JsonSerializer
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Dependency Module of Network.
 */
object NetworkModule {

    /**
     * Get HttpClient of Ktor.
     *
     * @param context Context
     * @param jsonSerializer JsonSerializer
     * @return HttpClient
     */
    fun getHttpClient(context: Context, jsonSerializer: JsonSerializer): HttpClient {
        return HttpClient(OkHttp) {
            engine {
                // Set log only for debug
                if (BuildConfig.DEBUG) {
                    addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    addInterceptor(ChuckInterceptor(context))
                }
            }
            install(JsonFeature) {
                // Set Json serializer
                serializer = jsonSerializer
            }
        }
    }

    /**
     * Get APIClient of Ktor.
     *
     * @param httpClient HttpClient
     * @return APIClient
     */
    fun getAPIClient(httpClient: HttpClient): APIClient {
        return APIClient(httpClient)
    }
}
