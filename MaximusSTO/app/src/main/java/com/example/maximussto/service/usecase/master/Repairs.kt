package com.example.maximussto.service.usecase.master

import com.example.maximussto.domain.entity.implementation.Master
import com.example.maximussto.domain.entity.implementation.Request
import com.example.maximussto.domain.entity.implementation.RequestStatus
import com.example.maximussto.domain.repository.implementation.RequestRepositoryImpl

class Repairs {

    fun getRequestsForMaster(master: Master, block: (requests: List<Request>) -> Unit) {
        block(RequestRepositoryImpl().readAll().filter {
            it.status == RequestStatus.WAIT_REPAIRS || it.master?.id == master.id
        })
    }

    fun changeRequestStatus(request: Request, master: Master?, newStatus: RequestStatus, block: (() -> Unit)? = null) {
        request.status = newStatus
        request.master = master
        RequestRepositoryImpl().update(request)
        block?.invoke()
    }
}