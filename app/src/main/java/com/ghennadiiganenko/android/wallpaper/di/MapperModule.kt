package com.ghennadiiganenko.android.wallpaper.di

import com.ghennadiiganenko.android.wallpaper.domain.mapper.WallpaperMapper
import org.koin.dsl.module

val mapperModule = module {
    single { WallpaperMapper() }
}