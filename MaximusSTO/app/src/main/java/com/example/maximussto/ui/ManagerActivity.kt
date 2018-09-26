package com.example.maximussto.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.maximussto.R
import com.example.maximussto.domain.entity.implementation.Request
import com.example.maximussto.domain.entity.implementation.RequestStatus
import com.example.maximussto.domain.entity.implementation.RequestType
import com.example.maximussto.service.usecase.manager.RequestManagment
import kotlinx.android.synthetic.main.activity_manager.*

class ManagerActivity : AppCompatActivity(), ManagerRequestViewHolder.OnRequestChangeListener {

    private val adapter = ManagerRequestItemsAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager)
        recycler.adapter = adapter
        updateData()
    }

    private fun updateData() {
        RequestManagment().getAllRequests { requests ->
            adapter.items.clear()
            adapter.items.addAll(requests)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onRequestTypeChange(request: Request, newType: RequestType) {
        RequestManagment().changeRequestType(request, newType) {
            updateData()
        }
    }

    override fun onRequestStatusChange(request: Request, newStatus: RequestStatus) {
        RequestManagment().changeRequestStatus(request, newStatus) {
            updateData()
        }
    }
}
