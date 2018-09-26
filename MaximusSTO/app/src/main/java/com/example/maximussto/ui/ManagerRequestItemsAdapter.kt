package com.example.maximussto.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.maximussto.R
import com.example.maximussto.domain.entity.implementation.Request

class ManagerRequestItemsAdapter(val listener: ManagerRequestViewHolder.OnRequestChangeListener) : RecyclerView.Adapter<ManagerRequestViewHolder>() {
    val items = mutableListOf<Request>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManagerRequestViewHolder {
        return ManagerRequestViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_request, parent, false),
            listener
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ManagerRequestViewHolder, position: Int) {
        holder.bind(items[position])
    }
}