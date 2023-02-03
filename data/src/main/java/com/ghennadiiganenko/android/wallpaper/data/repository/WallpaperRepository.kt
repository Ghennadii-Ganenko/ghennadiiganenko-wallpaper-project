package com.ghennadiiganenko.android.wallpaper.data.repository

import com.ghennadiiganenko.android.wallpaper.data.api.Result
import com.ghennadiiganenko.android.wallpaper.data.datasource.RemoteDataSource
import com.ghennadiiganenko.android.wallpaper.domain.mapper.WallpaperMapper
import com.ghennadiiganenko.android.wallpaper.domain.module.WallpaperEntity

class WallpaperRepository(
    private val remoteDataSource: RemoteDataSource,
    private val wallpaperMapper: WallpaperMapper
) {

    suspend fun getWallpapersList(): WallpaperEntity? {
        val result = remoteDataSource.getWallpapers()
        if (result.status == Result.Status.SUCCESS) {
            val data = result.data?: return null
            return wallpaperMapper.map(data)
        }
        return null
    }
}