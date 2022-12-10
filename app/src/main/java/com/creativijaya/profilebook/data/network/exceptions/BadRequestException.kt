package com.creativijaya.profilebook.data.network.exceptions

data class BadRequestException(val errorMessage: String?) : Exception(errorMessage)
