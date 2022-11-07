package com.nutversion1.nutandroidshowcase.api

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import okhttp3.logging.HttpLoggingInterceptor

const val API_LOGGER_TAG = "ApiLogger"

class ApiLogger : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        if (message.startsWith("{") || message.startsWith("[")) {
            try {
                val prettyPrintJson = GsonBuilder().setPrettyPrinting()
                    .create().toJson(JsonParser.parseString(message))
                Log.d(API_LOGGER_TAG, prettyPrintJson)
            } catch (m: JsonSyntaxException) {
                Log.d(API_LOGGER_TAG, message)
            }
        } else {
            Log.d(API_LOGGER_TAG, message)
            return
        }
    }
}