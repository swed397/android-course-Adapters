package com.android.course.adapters.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.android.course.adapters.R
import com.android.course.adapters.adapters.ConverterRecyclerViewAdapter
import com.android.course.adapters.converter.ConverterInteractor
import com.android.course.adapters.converter.ConverterUnit
import com.android.course.adapters.converter.ConverterValue
import com.android.course.adapters.getData


class ConverterActivity : AppCompatActivity() {

    private lateinit var converterRecyclerView: RecyclerView
    private lateinit var values: MutableList<ConverterValue>
    private lateinit var recyclerViewAdapter: ConverterRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)

        values = intent.extras?.getData<ArrayList<ConverterUnit>>(MainActivity.KEY_UNITS)
            .orEmpty()
            .map { converterUnit ->
                ConverterValue(
                    converterUnit = converterUnit,
                    value = converterUnit.fromBaseRate,
                )
            }
            .toMutableList()

        recyclerViewAdapter = ConverterRecyclerViewAdapter(
            converterValuesList = values,
            onFocus = ::onFocusAdapterItem,
            onChange = ::onChangeAdapterItem
        )

        converterRecyclerView = findViewById(R.id.converter_recycler_view)
        converterRecyclerView.adapter = recyclerViewAdapter
    }

    private fun onFocusAdapterItem(pos: Int) {
        val newList = ArrayList(values)
        val item = newList.removeAt(pos)
        newList.add(0, item)

        recyclerViewAdapter.setData(newList)
    }

    private fun onChangeAdapterItem(pos: Int, text: String) {
        Handler(Looper.getMainLooper()).postDelayed({
            val copyList = values
            try {
                copyList[pos].value = text.toDouble()
            } catch (e: Exception) {
                Toast.makeText(this, "Недопустимые символы", Toast.LENGTH_LONG)
                    .show()
            }
            val newList = ConverterInteractor().convert(copyList)

            recyclerViewAdapter.setData(newList)
        }, 2000)
    }
}