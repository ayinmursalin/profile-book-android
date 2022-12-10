package com.autopedia.android.data.network.exceptions

data class TooManyRequestException(val errorMessage: String?) : Exception(errorMessage)
