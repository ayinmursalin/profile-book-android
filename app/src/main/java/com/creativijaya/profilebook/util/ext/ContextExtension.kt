package com.creativijaya.profilebook.util.ext

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.os.Environment
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import java.io.File

fun Context.getDimen(@DimenRes dimenRes: Int): Int {
    return resources.getDimensionPixelOffset(dimenRes)
}

fun Context.getColorCompat(@ColorRes colorRes: Int): Int {
    return ContextCompat.getColor(this, colorRes)
}

fun Context.getDrawableCompat(@DrawableRes drawableRes: Int): Drawable? {
    return ContextCompat.getDrawable(this, drawableRes)
}

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Context.isAllowedPermission(permission: String): Boolean {
    return ContextCompat.checkSelfPermission(this, permission) ==
            PackageManager.PERMISSION_GRANTED
}

fun Context.createNewImage(fileName: String): File? {
    val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)

    return File.createTempFile(fileName, ".png", storageDir)
}

fun Context.createNewPdf(fileName: String): File {
    val storageDir = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)

    return File("${storageDir?.absoluteFile}/${fileName}_${System.currentTimeMillis()}.pdf")
}

fun Context.createNewExcel(fileName: String): File {
    val storageDir = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)

    return File("${storageDir?.absoluteFile}/${fileName}_${System.currentTimeMillis()}.xlsx")
}

