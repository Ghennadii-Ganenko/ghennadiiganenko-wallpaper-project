package com.ghennadiiganenko.android.wallpaper.di

import com.ghennadiiganenko.android.wallpaper.fragments.categories.usecase.GetWallpapersUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetWallpapersUseCase( get()) }
}