package com.creativijaya.profilebook.data.network.exceptions

data class InternalServerException(val errorMessage: String?) : Exception(errorMessage)
