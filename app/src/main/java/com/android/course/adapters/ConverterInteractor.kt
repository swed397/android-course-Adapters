package com.android.course.adapters

class ConverterInteractor {

    fun convert(converterValues: List<ConverterValue>): List<ConverterValue> {
        val mutableValues = converterValues.toMutableList()
        val baseValue = mutableValues.removeFirst()

        for (i in mutableValues.indices) {
            mutableValues[i] = mutableValues[i].copy(
                value = (baseValue.value * baseValue.converterUnit.toBaseRate * mutableValues[i].converterUnit.fromBaseRate)
//                    .setScale(2, RoundingMode.UP).toDouble()
            )
        }
        mutableValues.sortBy { it.value }
        mutableValues.add(0, baseValue)
        return mutableValues
    }

    fun startList(quantity: Quantity): List<ConverterValue> =
        convert(quantity.convertUnits.map { ConverterValue(it, 100.0) })
}