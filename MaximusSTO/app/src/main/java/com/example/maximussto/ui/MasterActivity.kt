package com.example.maximussto.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.maximussto.R
import com.example.maximussto.domain.entity.implementation.Master
import com.example.maximussto.domain.entity.implementation.Request
import com.example.maximussto.domain.entity.implementation.RequestStatus
import com.example.maximussto.domain.repository.implementation.MasterRepositoryImpl
import com.example.maximussto.service.usecase.master.Repairs
import kotlinx.android.synthetic.main.activity_master.*

class MasterActivity : AppCompatActivity(), MasterRequestViewHolder.OnRequestChangeListener {

    private val adapter = MasterRequestItemsAdapter(this)
    private var master: Master? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_master)
        master = MasterRepositoryImpl().read(intent.getStringExtra("master_id"))
        recycler.adapter = adapter
        updateData()
    }

    private fun updateData() {
        val master = master ?: return
        Repairs().getRequestsForMaster(master) { requests ->
            adapter.items.clear()
            adapter.items.addAll(requests)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onRequestStatusChange(request: Request, newStatus: RequestStatus) {
        val master = master ?: return
        Repairs().changeRequestStatus(request, master, newStatus) {
            updateData()
        }
    }
}
