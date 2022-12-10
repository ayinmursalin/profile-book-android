package com.creativijaya.profilebook.data.network.exceptions

data class NotFoundException(val errorMessage: String?) : Exception(errorMessage)
