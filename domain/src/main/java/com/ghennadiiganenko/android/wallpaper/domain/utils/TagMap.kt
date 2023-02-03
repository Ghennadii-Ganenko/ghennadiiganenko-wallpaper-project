package com.ghennadiiganenko.android.wallpaper.domain.utils

import com.ghennadiiganenko.android.wallpaper.domain.module.Hit
import com.ghennadiiganenko.android.wallpaper.domain.module.WallpaperEntity

fun WallpaperEntity.tagMap(tags: List<String>) : Map<String, List<Hit>> {
    return tags.associateWith { tag -> this.hits.filter { hit -> hit.hasTag(tag) }  }
}