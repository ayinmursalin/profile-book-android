package com.creativijaya.profilebook.util.ext

import com.airbnb.mvrx.*
import com.autopedia.android.data.network.exceptions.TooManyRequestException
import com.autopedia.android.data.network.exceptions.UnprocessableEntityException
import com.creativijaya.profilebook.data.network.exceptions.BadRequestException
import com.creativijaya.profilebook.data.network.exceptions.InternalServerException
import com.creativijaya.profilebook.data.network.exceptions.MethodNotAllowedException
import com.creativijaya.profilebook.data.network.exceptions.NotFoundException
import com.creativijaya.profilebook.data.network.exceptions.UnauthorizedException
import com.creativijaya.profilebook.data.network.responses.ErrorResponse
import org.koin.android.ext.android.inject
import retrofit2.HttpException
import java.io.File

inline fun <T> successOrError(block: () -> T): T {
    return try {
        block.invoke()
    } catch (e: HttpException) {
        val json = e.response()?.errorBody()?.string()
        val messageResponse = json?.safeGenerateModel<ErrorResponse>()

        when (e.code()) {
            400 -> throw BadRequestException(messageResponse?.message)
            401 -> throw UnauthorizedException(messageResponse?.message)
            404 -> throw NotFoundException(messageResponse?.message)
            405 -> throw MethodNotAllowedException(messageResponse?.message)
            422 -> throw UnprocessableEntityException(messageResponse?.message)
            429 -> throw TooManyRequestException(messageResponse?.message)
            500 -> throw InternalServerException(messageResponse?.message)
            else -> throw e
        }
    } catch (e: Exception) {
        throw e
    }
}

inline fun <T, R> T.mapTo(block: (T) -> R): R {
    return block.invoke(this)
}

inline fun <reified T : Any> ViewModelContext.scopeInject(): Lazy<T> {
    return if (this is FragmentViewModelContext)
        this.fragment.inject()
    else
        this.activity.inject()
}

fun <T> Async<T>.isUninitialized(): Boolean {
    return this is Uninitialized
}

fun <T> Async<T>.isLoading(): Boolean {
    return this is Loading
}

fun <T> Async<T>.isSuccess(): Boolean {
    return this is Success
}

fun <T> Async<T>.isFail(): Boolean {
    return this is Fail
}

sealed class Download {
    data class Progress(val percent: Int) : Download()
    data class Finished(val file: File) : Download()
}
