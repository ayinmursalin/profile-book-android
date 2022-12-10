package com.autopedia.android.data.network.exceptions

data class UnprocessableEntityException(val errorMessage: String?) : Exception(errorMessage)
