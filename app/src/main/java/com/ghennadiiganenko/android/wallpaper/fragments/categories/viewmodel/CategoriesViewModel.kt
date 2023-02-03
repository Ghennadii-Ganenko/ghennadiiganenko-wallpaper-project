package com.ghennadiiganenko.android.wallpaper.fragments.categories.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ghennadiiganenko.android.wallpaper.domain.module.WallpaperEntity
import com.ghennadiiganenko.android.wallpaper.fragments.categories.usecase.GetWallpapersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoriesViewModel (
    private val getWallpapersUseCase: GetWallpapersUseCase
) : ViewModel() {

    private var _wallpapersList: MutableLiveData<WallpaperEntity> = MutableLiveData<WallpaperEntity>()
    val wallpapersList: LiveData<WallpaperEntity> = _wallpapersList

    fun getWallpapersList() = viewModelScope.launch(Dispatchers.IO) {
            val result = getWallpapersUseCase()
                withContext(Dispatchers.Main) {
                    result?.let {
                        _wallpapersList.value = result ?: WallpaperEntity(listOf(), 0, 0)
                    }
                }

        }
}