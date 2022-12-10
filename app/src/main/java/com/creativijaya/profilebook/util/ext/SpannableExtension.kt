package com.creativijaya.profilebook.util.ext

import android.graphics.Typeface
import android.text.ParcelableSpan
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.SubscriptSpan
import android.text.style.SuperscriptSpan

fun SpannableStringBuilder.applySpan(
    textToSpanned: String,
    style: ParcelableSpan,
): SpannableStringBuilder {
    val startIndex = this.indexOf(textToSpanned)
    if (startIndex == -1) return this
    val endIndex = startIndex + textToSpanned.length

    return this.apply {
        setSpan(
            style,
            startIndex,
            endIndex,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
}

fun Spannable.boldTextSpan(textToBold: String): Spannable {
    return SpannableStringBuilder(this).applySpan(
        textToBold,
        StyleSpan(Typeface.BOLD)
    )
}

fun Spannable.italicTextSpan(textToItalic: String): Spannable {
    return SpannableStringBuilder(this).applySpan(
        textToItalic,
        StyleSpan(Typeface.ITALIC)
    )
}

fun Spannable.coloredTextSpan(textToColored: String, color: Int): Spannable {
    return SpannableStringBuilder(this).applySpan(
        textToColored,
        ForegroundColorSpan(color)
    )
}

fun Spannable.coloredBackgroundSpan(textToColoredInBackground: String, color: Int): Spannable {
    return SpannableStringBuilder(this).applySpan(
        textToColoredInBackground,
        BackgroundColorSpan(color)
    )
}

fun Spannable.superscriptSpan(textToQuoted: String): Spannable {
    return SpannableStringBuilder(this).applySpan(
        textToQuoted,
        SuperscriptSpan()
    )
}

fun Spannable.subscriptSpan(textToQuoted: String): Spannable {
    return SpannableStringBuilder(this).applySpan(
        textToQuoted,
        SubscriptSpan()
    )
}
