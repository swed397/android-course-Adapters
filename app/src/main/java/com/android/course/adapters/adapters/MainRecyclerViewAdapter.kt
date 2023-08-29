package com.android.course.adapters.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.course.adapters.R
import com.android.course.adapters.converter.Quantity

class MainRecyclerViewAdapter(
    private val quantitiesList: List<Quantity>,
    private val onClickMainRecycler: (position: Int) -> Unit
) : RecyclerView.Adapter<MainRecyclerViewAdapter.MainRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyclerViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_recycler_view_item, parent, false)

        return MainRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = quantitiesList.size

    override fun onBindViewHolder(holder: MainRecyclerViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class MainRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView by lazy { itemView.findViewById(R.id.main_recycler_view_item) }

        init {
            textView.setOnClickListener {
                onClickMainRecycler.invoke(adapterPosition)
            }
        }

        fun bind(position: Int) {
            textView.text = textView.resources.getString(quantitiesList[position].label)
        }
    }
}