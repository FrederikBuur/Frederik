package com.buur.frederikapp.api

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.buur.frederikapp.BuildConfig
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ErrorHandler {

    const val TAG = "ErrorHandler"

    fun handleError(error: Throwable, context: Context? = null) {

        var userErrorMessage: String? = null

        val errorMessage = when (error) {
            is HttpException -> {
                "${error.code()} ${error.message()}, " +
                        when (error.code()) {
                            403 -> {
                                "Maybe renew API key"
                            }
                            404 -> {
                                "Not Found"
                            }
                            else -> {
                                "Unsupported HttpException"
                            }
                        }
            }
            is UnknownHostException, is SocketTimeoutException -> {
                userErrorMessage = "No internet connection"
                userErrorMessage
            }
            else -> {
                "Unsupported Error: ${error.message}"
            }
        }
        Log.e(TAG, "Exception has occurred: $errorMessage")

        // notify user/developer about error
        context?.let { con ->
            if (BuildConfig.DEBUG) {
                Toast.makeText(con, errorMessage, Toast.LENGTH_SHORT).show()
            } else {
                userErrorMessage?.let { message ->
                    Toast.makeText(con, message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}