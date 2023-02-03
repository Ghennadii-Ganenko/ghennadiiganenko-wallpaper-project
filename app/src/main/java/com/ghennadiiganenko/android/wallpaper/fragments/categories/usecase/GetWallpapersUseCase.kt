package com.ghennadiiganenko.android.wallpaper.fragments.categories.usecase

import com.ghennadiiganenko.android.wallpaper.data.repository.WallpaperRepository
import com.ghennadiiganenko.android.wallpaper.domain.module.WallpaperEntity

class GetWallpapersUseCase(
    private val wallpaperRepository: WallpaperRepository
) {
    suspend operator fun invoke(): WallpaperEntity? = wallpaperRepository.getWallpapersList()
}