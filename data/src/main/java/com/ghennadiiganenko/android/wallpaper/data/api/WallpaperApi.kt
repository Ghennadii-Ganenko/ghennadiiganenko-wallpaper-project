package com.ghennadiiganenko.android.wallpaper.data.api

import com.ghennadiiganenko.android.wallpaper.data.WallpaperData
import retrofit2.Response
import retrofit2.http.GET

interface WallpaperApi {

    @GET("?key=33106230-b104905cd7ff74ed17e2229af")
    suspend fun getWallpapers(): Response<WallpaperData>
}