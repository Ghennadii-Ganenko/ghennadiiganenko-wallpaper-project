package com.ghennadiiganenko.android.wallpaper.domain.mapper

interface IMapper<I, O> {
    fun map(input: I): O
}