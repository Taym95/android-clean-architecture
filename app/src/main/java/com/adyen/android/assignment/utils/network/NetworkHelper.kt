package com.adyen.android.assignment.utils.network

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

private const val TIME_OUT = 60L
private const val CACHE_SIZE = 10 * 1024 * 1024L
private const val CACHE_DIRECTORY = "http"


fun createMoshi(): Moshi {
    return Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()
}

fun createCache(context: Context): Cache = Cache(
    directory = File(context.cacheDir, CACHE_DIRECTORY),
    maxSize = CACHE_SIZE
)

inline fun <reified T> createRetrofitWithMoshi(
    okHttpClient: OkHttpClient,
    moshi: Moshi,
    baseUrl: String
): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
    return retrofit.create(T::class.java)
}


fun createOkHttpClient(
    isCache: Boolean = false,
    context: Context
): OkHttpClient {
    return OkHttpClient.Builder().apply {
        if (isCache) cache(createCache(context))
        connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        readTimeout(TIME_OUT, TimeUnit.SECONDS)
        writeTimeout(TIME_OUT, TimeUnit.SECONDS)
        followSslRedirects(true)
        followRedirects(true)
        retryOnConnectionFailure(true)
    }.build()
}
