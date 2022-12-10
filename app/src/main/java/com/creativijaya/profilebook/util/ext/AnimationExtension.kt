package com.creativijaya.profilebook.util.ext

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.app.Activity
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.animation.Interpolator
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import com.creativijaya.profilebook.MyAppConstant.Animation


fun View.animateTranslateAndScale(
    activity: Activity? = null,
    translate: Float = Animation.HALF,
    fromScale: Float = Animation.FULL,
    toScale: Float = Animation.HUGE,
    animationDuration: Long = Animation.DURATION_FAST,
    animationInterpolator: Interpolator = DecelerateInterpolator(),
    onStartListener: () -> Unit = {},
    onEndListener: () -> Unit = {}
) {
    val width = activity?.window?.decorView?.width.orZero() * translate
    val translationX = PropertyValuesHolder.ofFloat(
        View.TRANSLATION_X, width
    )
    val scaleWidth = PropertyValuesHolder.ofFloat(
        View.SCALE_X, fromScale, toScale
    )
    val scaleHeight = PropertyValuesHolder.ofFloat(
        View.SCALE_Y, fromScale, toScale
    )

    ObjectAnimator.ofPropertyValuesHolder(
        this,
        translationX,
        scaleWidth,
        scaleHeight
    ).apply {
        interpolator = animationInterpolator
        duration = animationDuration

        doOnStart {
            onStartListener.invoke()
        }

        doOnEnd {
            onEndListener.invoke()
        }

        start()
    }
}

fun View.animateRotate(
    fromDegree: Float = Animation.ZERO,
    toDegree: Float = Animation.ROTATE_45,
    animationDuration: Long = Animation.DURATION_FAST,
    animationInterpolator: Interpolator = DecelerateInterpolator(),
    onStartListener: () -> Unit = {},
    onEndListener: () -> Unit = {}
) {
    ObjectAnimator.ofFloat(
        this,
        View.ROTATION,
        fromDegree,
        toDegree
    ).apply {
        interpolator = animationInterpolator
        duration = animationDuration

        doOnStart {
            onStartListener.invoke()
        }

        doOnEnd {
            onEndListener.invoke()
        }

        start()
    }
}
