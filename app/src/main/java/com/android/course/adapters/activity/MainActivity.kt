package com.android.course.adapters.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.android.course.adapters.R
import com.android.course.adapters.adapters.MainRecyclerViewAdapter
import com.android.course.adapters.converter.ConverterRepository

class MainActivity : AppCompatActivity() {

    private lateinit var mainRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainRecyclerViewAdapter =
            MainRecyclerViewAdapter(ConverterRepository().availableQuantities, this)

        mainRecyclerView = findViewById(R.id.main_recycler_view)
        mainRecyclerView.adapter = mainRecyclerViewAdapter
        mainRecyclerView.addItemDecoration(createDividerItemDecorator())
    }

    private fun createDividerItemDecorator(): ItemDecoration {
        val dividerItemDecoration = DividerItemDecoration(this, RecyclerView.VERTICAL)
        dividerItemDecoration.setDrawable(
            ResourcesCompat.getDrawable(
                resources,
                R.drawable.main_recycler_view_item_decorator,
                null
            ) ?: throw RuntimeException("No such resources")
        )
        return dividerItemDecoration
    }
}