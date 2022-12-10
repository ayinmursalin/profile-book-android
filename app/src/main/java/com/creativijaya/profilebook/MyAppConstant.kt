package com.creativijaya.profilebook

object MyAppConstant {

    const val APP_PREFERENCE = ".my-skeleton-preference"
    const val APP_PROVIDER = "com.creativijaya.profilebook.fileprovider"
    const val APP_DATABASE_NAME = ".skeleton-db"
    const val APP_DATABASE_VERSION = 1
    const val CURRENCY_FORMAT = "#,###.##"

    const val DEBOUNCE_TIME = 250

    object SpannableConstant {
        const val QUOTED_SPAN_STRIP_WIDTH = 20
        const val QUOTED_SPAN_STRIP_DISTANCE_TO_TEXT = 20
    }

    object Animation {
        const val ZERO = 0f
        const val MEDIUM = 0.4f
        const val HALF = 0.5f
        const val BIG = 0.7f
        const val HUGE = 0.8f
        const val FULL = 1f

        const val ROTATE_45 = 45f
        const val ROTATE_180 = 180f

        const val SLIGHT_DURATION = 50L
        const val DURATION_FAST = 150L
    }
}
