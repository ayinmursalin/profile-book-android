package com.creativijaya.profilebook.presentation.base

import android.Manifest
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.coroutineScope
import com.airbnb.mvrx.Async
import com.airbnb.mvrx.DeliveryMode
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.RedeliverOnStart
import com.airbnb.mvrx.Success
import com.autopedia.android.data.network.exceptions.TooManyRequestException
import com.autopedia.android.data.network.exceptions.UnprocessableEntityException
import com.creativijaya.profilebook.R
import com.creativijaya.profilebook.data.local.prefs.AppPreferences
import com.creativijaya.profilebook.data.network.exceptions.BadRequestException
import com.creativijaya.profilebook.data.network.exceptions.InternalServerException
import com.creativijaya.profilebook.data.network.exceptions.MethodNotAllowedException
import com.creativijaya.profilebook.data.network.exceptions.NotFoundException
import com.creativijaya.profilebook.data.network.exceptions.UnauthorizedException
import com.creativijaya.profilebook.util.ext.isAllowedPermission
import com.creativijaya.profilebook.util.ext.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import kotlin.reflect.KProperty1

abstract class BaseFragment(
    @LayoutRes layoutRes: Int
) : Fragment(layoutRes), MavericksView {

    private val preferences: AppPreferences by inject()
    private var progressView: View? = null

    override fun onDestroyView() {
        showFullScreenLoading(false)
        super.onDestroyView()
    }

    protected fun showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        requireContext().showToast(message, duration)
    }

    protected fun handleError(throwable: Throwable) {
        when (throwable) {
            is BadRequestException -> {
                showToast(throwable.errorMessage.orEmpty())
            }
            is InternalServerException -> {
                showToast(throwable.errorMessage.orEmpty())
            }
            is MethodNotAllowedException -> {
                showToast(throwable.errorMessage.orEmpty())
            }
            is NotFoundException -> {
                showToast("URL Not Found")
            }
            is UnauthorizedException -> {
                showToast(throwable.errorMessage.orEmpty())
                preferences.setToken(null)
                // Return to Login Screen
            }
            is UnprocessableEntityException -> {
                showToast(throwable.errorMessage.orEmpty())
            }
            is TooManyRequestException -> {
                showToast(throwable.errorMessage.orEmpty())
            }
            else -> {
                showToast(throwable.message.orEmpty())
                throwable.printStackTrace()
            }
        }
    }

    protected fun showFullScreenLoading(isLoading: Boolean) {
        val viewGroup = requireActivity().window.decorView as ViewGroup
        if (progressView != null) {
            viewGroup.removeView(progressView)
        } else {
            progressView = LayoutInflater
                .from(requireActivity())
                .inflate(R.layout.dialog_loading_screen, viewGroup, false)
        }
        if (isLoading) viewGroup.addView(progressView)
        else {
            viewGroup.removeView(progressView)
            progressView = null
        }
    }

    protected fun safeRun(
        delayTime: Long = 0L,
        block: suspend () -> Unit
    ) {
        viewLifecycleOwner.lifecycle.coroutineScope.launch(Dispatchers.Main) {
            delay(delayTime)
            block.invoke()
        }
    }

    protected fun isCameraAllowed(): Boolean {
        return requireActivity().isAllowedPermission(Manifest.permission.CAMERA)
    }

    protected fun isReadStorageAllowed(): Boolean {
        return requireActivity().isAllowedPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    protected fun <S : MavericksState, T> MavericksViewModel<S>.onCommonAsync(
        asyncProp: KProperty1<S, Async<T>>,
        deliveryMode: DeliveryMode = RedeliverOnStart,
        onFail: (suspend (Throwable) -> Unit)? = null,
        onSuccess: (suspend (T) -> Unit)? = null
    ) {
        this.onEach(asyncProp, deliveryMode) {
            when (it) {
                is Loading -> showFullScreenLoading(true)
                is Success -> {
                    showFullScreenLoading(false)
                    onSuccess?.invoke(it.invoke())
                }
                is Fail -> onFail?.invoke(it.error)
                else -> Unit
            }
        }
    }

}

