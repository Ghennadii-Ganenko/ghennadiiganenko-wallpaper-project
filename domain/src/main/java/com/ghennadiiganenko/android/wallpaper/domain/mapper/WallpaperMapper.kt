package com.ghennadiiganenko.android.wallpaper.domain.mapper

import com.ghennadiiganenko.android.wallpaper.domain.module.WallpaperEntity
import com.ghennadiiganenko.android.wallpaper.domain.network.IWallpaper

class WallpaperMapper : IMapper<IWallpaper, WallpaperEntity>  {
    override fun map(input: IWallpaper): WallpaperEntity = WallpaperEntity(
        hits = input.hits,
        total = input.total,
        totalHits = input.totalHits
    )
}