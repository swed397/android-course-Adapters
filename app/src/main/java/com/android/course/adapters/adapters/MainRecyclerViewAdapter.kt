package com.android.course.adapters.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.course.adapters.converter.Quantity
import com.android.course.adapters.R
import com.android.course.adapters.activity.ConverterActivity

class MainRecyclerViewAdapter(
    private val quantitiesList: List<Quantity>,
    private val context: Context
) : RecyclerView.Adapter<MainRecyclerViewAdapter.MainRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyclerViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_recycler_view_item, parent, false)

        return MainRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = quantitiesList.size

    override fun onBindViewHolder(holder: MainRecyclerViewHolder, position: Int) {
        holder.textView.text = holder.textView.resources.getString(quantitiesList[position].label)

        holder.textView.setOnClickListener {
            val intent = Intent(context, ConverterActivity::class.java)
            intent.putExtra("units", quantitiesList[position].convertUnits)
            context.startActivity(intent)
        }
    }

    class MainRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView

        init {
            textView = itemView.findViewById(R.id.main_recycler_view_item)
        }
    }
}