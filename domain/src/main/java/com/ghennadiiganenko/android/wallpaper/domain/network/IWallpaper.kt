package com.ghennadiiganenko.android.wallpaper.domain.network

import com.ghennadiiganenko.android.wallpaper.domain.module.Hit

interface IWallpaper {
    val hits: List<Hit>
    val total: Int
    val totalHits: Int
}