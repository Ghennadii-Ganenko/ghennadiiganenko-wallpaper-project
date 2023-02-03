package com.ghennadiiganenko.android.wallpaper.data.datasource

import com.ghennadiiganenko.android.wallpaper.data.api.BaseDataSource
import com.ghennadiiganenko.android.wallpaper.data.api.WallpaperApi

class RemoteDataSource  constructor(
    private val wallpaperApi: WallpaperApi
): BaseDataSource() {

    suspend fun getWallpapers() = getResult { wallpaperApi.getWallpapers()}
}