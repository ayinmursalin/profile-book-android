package com.creativijaya.profilebook.data.network.exceptions

data class MethodNotAllowedException(val errorMessage: String?) : Exception(errorMessage)
