package com.android.course.adapters.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.android.course.adapters.R
import com.android.course.adapters.adapters.ConverterRecyclerViewAdapter
import com.android.course.adapters.converter.ConverterUnit

class ConverterActivity : AppCompatActivity() {

    private lateinit var converterRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)

        val units = intent.extras?.getSerializable("units") as ArrayList<ConverterUnit>
        val converterRecyclerViewAdapter = ConverterRecyclerViewAdapter(units)

        converterRecyclerView = findViewById(R.id.converter_recycler_view)
        converterRecyclerView.adapter = converterRecyclerViewAdapter
    }
}