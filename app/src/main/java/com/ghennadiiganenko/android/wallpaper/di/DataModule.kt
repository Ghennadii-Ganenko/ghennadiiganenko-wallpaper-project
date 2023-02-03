package com.ghennadiiganenko.android.wallpaper.di

import com.ghennadiiganenko.android.wallpaper.data.repository.WallpaperRepository
import org.koin.dsl.module

val dataModule = module {
    single {
        WallpaperRepository( get(), get() )
    }
}