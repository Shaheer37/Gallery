package com.gallery.data.remote

import com.gallery.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object DataFactory{

    fun makeService(
        baseUrl: HttpUrl = BuildConfig.ImageApiBaseUrl.toHttpUrl(),
        json: Json,
        showLogs: Boolean = true): GalleryApi {
        val okHttpClient = makeOkHttpClient(
                makeLoggingInterceptor(showLogs)
        )
        return makeService(baseUrl, okHttpClient, json)
    }


    @ExperimentalSerializationApi
    private fun makeService(baseUrl: HttpUrl, okHttpClient: OkHttpClient, json: Json): GalleryApi {
        val contentType = "application/json".toMediaType()
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
        return retrofit.create(GalleryApi::class.java)
    }

    private fun makeOkHttpClient(
            httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }


    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor{
        val logging = HttpLoggingInterceptor()
        logging.level = if(isDebug){
            HttpLoggingInterceptor.Level.BODY
        } else{
            HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }
}