package com.creativijaya.profilebook.util.ext

fun String?.orEmpty(): String = this ?: ""
fun Long?.orZero(): Long = this ?: 0
fun Int?.orZero(): Int = this ?: 0
fun Boolean?.orFalse(): Boolean = this ?: false
fun Pair<Int, Int>?.orEmpty() = this ?: Pair(0, 0)
fun <T> T?.isNull() = this == null
fun <T> T?.isNotNull() = this != null
inline fun <reified T> List<T>?.orEmpty(): List<T> = this ?: emptyList()
inline fun <reified T> Array<T>?.orEmpty(): Array<T> = this ?: emptyArray()
