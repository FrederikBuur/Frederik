package com.buur.frederikapp.api

import retrofit2.HttpException

object ErrorHandler {

    fun handleError(error: Throwable): String? {

        return if (error is HttpException) {
            "${error.code()}: ${error.message()}"
        } else {
            "Error: ${error.message}"
        }

    }


}