package com.android.course.adapters.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.android.course.adapters.R
import com.android.course.adapters.adapters.ConverterRecyclerViewAdapter
import com.android.course.adapters.converter.ConverterInteractor
import com.android.course.adapters.converter.ConverterUnit
import com.android.course.adapters.converter.ConverterValue

class ConverterActivity : AppCompatActivity() {

    private lateinit var converterRecyclerView: RecyclerView
    private lateinit var values: ArrayList<ConverterValue>
    private lateinit var recyclerViewAdapter: ConverterRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)

        val units = intent.extras?.getSerializable("units") as ArrayList<ConverterUnit>
        values = ArrayList(units.map { ConverterValue(it, it.fromBaseRate) })

        recyclerViewAdapter = ConverterRecyclerViewAdapter(
            values,
            { pos ->
                onFocusAdapterItem(pos)
            },
            { pos, editText ->
                onChangeAdapterItem(pos, editText)
            })

        converterRecyclerView = findViewById(R.id.converter_recycler_view)
        converterRecyclerView.adapter = recyclerViewAdapter
    }

    private fun onFocusAdapterItem(pos: Int) {
        val newList = ArrayList(values)
        val item = newList.removeAt(pos)
        newList.add(0, item)

        recyclerViewAdapter.setData(newList)
    }

    private fun onChangeAdapterItem(pos: Int, editText: EditText) {
        Handler(Looper.getMainLooper()).postDelayed({
            val copyList = values
            try {
                copyList[pos].value = editText.text.toString().toDouble()
            } catch (e: Exception) {
                Toast.makeText(this, "Недопустимые символы", Toast.LENGTH_LONG)
                    .show()
            }
            val newList = ConverterInteractor().convert(copyList)

            recyclerViewAdapter.setData(newList)
        }, 2000)
    }
}