package com.creativijaya.profilebook.util.ext

import android.util.Log
import android.util.Patterns
import com.creativijaya.profilebook.domain.models.dialog.*
import com.google.gson.Gson

fun String.isValidEmail(): Boolean {
    return this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.uppercaseFirstChar(): String {
    return this.lowercase().replaceFirstChar { it.uppercase() }
}

inline fun <reified T> String.safeGenerateModel(): T? {
    return try {
        Gson().fromJson(this, T::class.java)
    } catch (e: Exception) {
        null
    }
}

inline fun <reified T> callOrNull(block: () -> T) = try {
    block.invoke()
} catch (e: Exception) {
    Log.d("DEBUG_MAIN", "error: ${e.message}")
    null
}

fun String.toParcelable() = StringParcelable(data = this)

fun Int.toParcelable() = IntParcelable(data = this)

fun Long.toParcelable() = LongParcelable(data = this)

fun Double.toParcelable() = DoubleParcelable(data = this)

fun Boolean.toParcelable() = BoolParcelable(data = this)
