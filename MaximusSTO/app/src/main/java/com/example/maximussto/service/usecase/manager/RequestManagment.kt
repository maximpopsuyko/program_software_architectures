package com.example.maximussto.service.usecase.manager

import com.example.maximussto.domain.entity.implementation.Request
import com.example.maximussto.domain.entity.implementation.RequestStatus
import com.example.maximussto.domain.entity.implementation.RequestType
import com.example.maximussto.domain.repository.implementation.RequestRepositoryImpl

class RequestManagment {

    fun getAllRequests(block: (requests: List<Request>) -> Unit) {
        block(RequestRepositoryImpl().readAll())
    }

    fun changeRequestStatus(request: Request, newStatus: RequestStatus, block: (() -> Unit)? = null) {
        request.status = newStatus
        RequestRepositoryImpl().update(request)
        block?.invoke()
    }

    fun changeRequestType(request: Request, newType: RequestType, block: (() -> Unit)? = null) {
        request.type = newType
        RequestRepositoryImpl().update(request)
        block?.invoke()
    }
}