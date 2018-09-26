package com.example.maximussto.ui

import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.maximussto.domain.entity.implementation.Request
import com.example.maximussto.domain.entity.implementation.RequestStatus
import com.example.maximussto.domain.entity.implementation.RequestType
import kotlinx.android.synthetic.main.item_request.view.*

class MasterRequestViewHolder(itemView: View, private val listener: OnRequestChangeListener) :
    RecyclerView.ViewHolder(itemView) {

    private var request: Request? = null

    fun bind(request: Request) {
        this.request = request
        itemView.request_text.text = request.text
        itemView.type_text.text = request.type.str
        itemView.status_text.text = request.status.str
        itemView.change_type.visibility = View.GONE
        itemView.change_status.setOnClickListener { showChangeStatusDialog() }
        if (request.status != RequestStatus.WAIT_REPAIRS && request.status != RequestStatus.IN_REPAIRS)
            itemView.change_status.visibility = View.GONE
    }

    private fun showChangeStatusDialog() {
        val values = when (request?.status) {
            RequestStatus.WAIT_REPAIRS -> listOf(RequestStatus.IN_REPAIRS)
            RequestStatus.IN_REPAIRS -> listOf(RequestStatus.COMPLETE)
            else -> return
        }
        AlertDialog.Builder(itemView.context)
            .setTitle("Изменение статуса запроса")
            .setItems(values.map(RequestStatus::str).toTypedArray()) { _, which ->
                val request = request ?: return@setItems
                listener.onRequestStatusChange(request, values[which])
            }
            .create()
            .show()
    }

    interface OnRequestChangeListener {
        fun onRequestStatusChange(request: Request, newStatus: RequestStatus)
    }
}