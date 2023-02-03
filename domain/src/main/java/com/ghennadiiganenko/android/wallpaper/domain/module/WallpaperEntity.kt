package com.ghennadiiganenko.android.wallpaper.domain.module

data class WallpaperEntity(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
) {
    fun makeAvailableTags() : List<String> {
        return hits.flatMap { it.getTags() }.distinct()
    }
}

data class Hit(
    val collections: Int,
    val comments: Int,
    val downloads: Int,
    val id: Int,
    val imageHeight: Int,
    val imageSize: Int,
    val imageWidth: Int,
    val largeImageURL: String,
    val likes: Int,
    val pageURL: String,
    val previewHeight: Int,
    val previewURL: String,
    val previewWidth: Int,
    val tags: String,
    val type: String,
    val user: String,
    val userImageURL: String,
    val user_id: Int,
    val views: Int,
    val webformatHeight: Int,
    val webformatURL: String,
    val webformatWidth: Int
) {
    fun hasTag(tag: String) = getTags().contains(tag)

    fun getTags(): List<String> {
        return tags.split(", ")
    }
}