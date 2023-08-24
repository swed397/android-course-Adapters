package com.android.course.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainRecyclerViewAdapter(private val quantitiesList: List<Quantity>) :
    RecyclerView.Adapter<MainRecyclerViewAdapter.MainRecyclerViewHolder>() {
    private var onClickListener: RecyclerVIewItemOnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyclerViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_recycler_view_item, parent, false)

        return MainRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = quantitiesList.size

    override fun onBindViewHolder(holder: MainRecyclerViewHolder, position: Int) {
        holder.textView.text = holder.textView.resources.getString(quantitiesList[position].label)

        holder.textView.setOnClickListener {
            if (onClickListener != null) onClickListener!!.onClick()
        }
    }

    fun setOnClickItemListener(onClickListener: RecyclerVIewItemOnClickListener) {
        this.onClickListener = onClickListener
    }

    class MainRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView

        init {
            textView = itemView.findViewById(R.id.main_recycler_view_item)
        }
    }
}