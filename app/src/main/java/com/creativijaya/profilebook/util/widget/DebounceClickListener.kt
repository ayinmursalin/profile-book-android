package com.creativijaya.profilebook.util.widget

import android.os.SystemClock
import android.view.View
import com.creativijaya.profilebook.MyAppConstant

class DebounceClickListener(
    private var defaultInterval: Int = MyAppConstant.DEBOUNCE_TIME,
    private val onSafeCLick: (View?) -> Unit
) : View.OnClickListener {
    private var lastTimeClicked: Long = 0

    override fun onClick(view: View?) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval) {
            return
        }

        lastTimeClicked = SystemClock.elapsedRealtime()
        onSafeCLick(view)
    }
}
