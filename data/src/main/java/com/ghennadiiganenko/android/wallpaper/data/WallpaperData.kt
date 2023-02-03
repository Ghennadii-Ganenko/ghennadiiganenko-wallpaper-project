package com.ghennadiiganenko.android.wallpaper.data

import com.ghennadiiganenko.android.wallpaper.domain.module.Hit
import com.ghennadiiganenko.android.wallpaper.domain.network.IWallpaper
import com.google.gson.annotations.SerializedName

class WallpaperData : IWallpaper{
    @SerializedName("hits")
    override val hits: List<Hit> = listOf()

    @SerializedName("total")
    override val total: Int = 0

    @SerializedName("totalHits")
    override val totalHits: Int = 0
}