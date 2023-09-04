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
    private var converterValuesList: List<ConverterValue>,
    private val onFocus: (position: Int) -> Unit,
    private val onChange: (position: Int, text: String) -> Unit
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

    fun setData(newList: List<ConverterValue>) {
        val diffUtil = ConverterDiffUtil(converterValuesList, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil, true)
        diffResult.dispatchUpdatesTo(this)
        converterValuesList = newList
    }

    inner class ConverterRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView by lazy { itemView.findViewById(R.id.converter_recycler_text_view) }
        private val editText: EditText by lazy { itemView.findViewById(R.id.converter_recycler_edit_text) }

        init {
            editText.setOnFocusChangeListener { view, hasFocus ->
                if (hasFocus) {
                    onFocus.invoke(bindingAdapterPosition)
                }
            }

            editText.addTextChangedListener {
                onChange.invoke(bindingAdapterPosition, editText.text.toString())
            }
        }

        fun bind(position: Int) {
            textView.text =
                textView.resources.getString(converterValuesList[position].converterUnit.label)
            editText.setText(converterValuesList[position].value.toString())
        }
    }

    private class ConverterDiffUtil(
        private val oldList: List<ConverterValue>,
        private val newList: List<ConverterValue>,
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition].converterUnit.label == newList[newItemPosition].converterUnit.label

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition].value == newList[newItemPosition].value
    }
}

