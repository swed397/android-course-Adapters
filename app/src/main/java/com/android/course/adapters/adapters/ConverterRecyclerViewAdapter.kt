package com.android.course.adapters.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.course.adapters.R
import com.android.course.adapters.converter.ConverterUnit

class ConverterRecyclerViewAdapter(private val converterUnitsList: List<ConverterUnit>) :
    RecyclerView.Adapter<ConverterRecyclerViewAdapter.ConverterRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConverterRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.converter_recycler_view_item, parent, false)

        return ConverterRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = converterUnitsList.size

    override fun onBindViewHolder(holder: ConverterRecyclerViewHolder, position: Int) {
        holder.textView.text =
            holder.textView.resources.getString(converterUnitsList[position].label)
        holder.editText.setText(converterUnitsList[position].toBaseRate.toString())
    }

    class ConverterRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView
        val editText: EditText

        init {
            textView = itemView.findViewById(R.id.converter_recycler_text_view)
            editText = itemView.findViewById(R.id.converter_recycler_edit_text)
        }
    }
}