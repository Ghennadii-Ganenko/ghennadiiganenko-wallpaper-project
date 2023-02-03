package com.ghennadiiganenko.android.wallpaper.di

import com.ghennadiiganenko.android.wallpaper.fragments.categories.viewmodel.CategoriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<CategoriesViewModel> {
        CategoriesViewModel(
            getWallpapersUseCase = get()
        )
    }
}