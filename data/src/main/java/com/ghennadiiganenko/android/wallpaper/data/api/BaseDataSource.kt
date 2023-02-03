package com.ghennadiiganenko.android.wallpaper.data.api

import retrofit2.Response
import java.util.logging.Logger

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                return if (body != null) Result.success(body)
                else Result.emptySuccess()
            }
            val serverError = response.errorBody()?.string() ?: "${response.code()}: ${response.message()}"
            return error(serverError, response.code())
        } catch (e: Exception) {
            return error(e.message ?: e.toString(), null)
        }
    }

    private fun <T> error(message: String, statusCode: Int?): Result<T> {
        return Result.error(message, statusCode = statusCode)
    }
}