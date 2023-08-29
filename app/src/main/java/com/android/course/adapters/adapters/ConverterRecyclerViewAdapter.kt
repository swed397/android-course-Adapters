package com.android.course.adapters.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.course.adapters.R
import com.android.course.adapters.converter.ConverterValue

class ConverterRecyclerViewAdapter(
    private var converterValuesList: ArrayList<ConverterValue>,
    private val onFocus: (position: Int) -> Unit,
    private val onChange: (position: Int, editText: EditText) -> Unit

) : RecyclerView.Adapter<ConverterRecyclerViewAdapter.ConverterRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConverterRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.converter_recycler_view_item, parent, false)

        return ConverterRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = converterValuesList.size

    override fun onBindViewHolder(holder: ConverterRecyclerViewHolder, position: Int) {
        holder.bind(position)
    }

    fun setData(newList: ArrayList<ConverterValue>) {
        val diffUtil = ConverterDiffUtil(converterValuesList, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        diffResult.dispatchUpdatesTo(this)
        converterValuesList = newList
    }

    inner class ConverterRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView by lazy { itemView.findViewById(R.id.converter_recycler_text_view) }
        private val editText: EditText by lazy { itemView.findViewById(R.id.converter_recycler_edit_text) }

        init {
            editText.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    onFocus.invoke(adapterPosition)
                }

            }
            editText.addTextChangedListener {
                onChange.invoke(adapterPosition, editText)
            }
        }

        fun bind(position: Int) {
            textView.text =
                textView.resources.getString(converterValuesList[position].converterUnit.label)
            editText.setText(converterValuesList[position].value.toString())
        }
    }
}

