package com.android.course.adapters

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import com.android.course.adapters.activity.MainActivity.Companion.KEY_UNITS
import java.io.Serializable

inline fun <reified T : Serializable> Bundle.getData(key: String): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getSerializable(KEY_UNITS, T::class.java)
    } else {
        getSerializable(KEY_UNITS) as T
    }

inline fun <reified T : Parcelable> Bundle.getParcelData(key: String): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelable(KEY_UNITS, T::class.java)
    } else {
        getParcelable(KEY_UNITS)
    }