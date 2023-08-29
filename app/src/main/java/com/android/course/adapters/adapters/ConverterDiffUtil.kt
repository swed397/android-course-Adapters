package com.android.course.adapters.adapters

import androidx.recyclerview.widget.DiffUtil
import com.android.course.adapters.converter.ConverterValue

class ConverterDiffUtil(
    private val oldList: ArrayList<ConverterValue>,
    private val newList: ArrayList<ConverterValue>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].converterUnit.label == newList[newItemPosition].converterUnit.label

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].value == newList[newItemPosition].value
}