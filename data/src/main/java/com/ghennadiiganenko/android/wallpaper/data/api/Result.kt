package com.ghennadiiganenko.android.wallpaper.data.api

data class Result<out T>(
    val status: Status,
    val data: T?,
    val message: String?,
    val statusCode: Int?
) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): Result<T> {
            return Result(Status.SUCCESS, data, null, null)
        }

        fun <T> emptySuccess(): Result<T> {
            return Result(Status.SUCCESS, null, null, null)
        }

        fun <T> error(message: String, data: T? = null, statusCode: Int? = -1): Result<T> {
            return Result(Status.ERROR, data, message, statusCode)
        }

        fun <T> loading(data: T? = null): Result<T> {
            return Result(Status.LOADING, data, null, null)
        }
    }
}