package com.android.course.adapters

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

data class ConverterValue(val converterUnit: ConverterUnit, var value: Double)

@Parcelize
data class ConverterUnit(@StringRes val label: Int, val toBaseRate: Double) : Parcelable {

    @IgnoredOnParcel
    val fromBaseRate: Double = 1.0 / toBaseRate
}

@Parcelize
data class Quantity(@StringRes val label: Int, val convertUnits: List<ConverterUnit>) : Parcelable

