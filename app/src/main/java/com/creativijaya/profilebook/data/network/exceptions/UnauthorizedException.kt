package com.creativijaya.profilebook.data.network.exceptions

data class UnauthorizedException(val errorMessage: String?) : Exception(errorMessage)
