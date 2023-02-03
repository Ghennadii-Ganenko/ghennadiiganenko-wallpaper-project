package com.ghennadiiganenko.android.wallpaper.di

import com.ghennadiiganenko.android.wallpaper.data.api.WallpaperApi
import com.ghennadiiganenko.android.wallpaper.data.datasource.RemoteDataSource
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

val remoteModule = module {
    single { RemoteDataSource(get()) }

    single { provideWallpaperService(get()) }
    single { provideDefaultOkHttpClient() }
}

fun provideWallpaperService(okHttpClient: OkHttpClient): WallpaperApi = Retrofit.Builder()
    .baseUrl("https://pixabay.com/api/")
    .client(okHttpClient)
    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(WallpaperApi::class.java)

fun provideDefaultOkHttpClient() = OkHttpClient.Builder()
    .connectTimeout(30, TimeUnit.SECONDS)
    .callTimeout(30, TimeUnit.SECONDS)
    .readTimeout(30, TimeUnit.SECONDS)
    .writeTimeout(30, TimeUnit.SECONDS)
    .build()