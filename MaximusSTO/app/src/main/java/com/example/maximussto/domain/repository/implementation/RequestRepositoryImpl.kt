package com.example.maximussto.domain.repository.implementation

import com.example.maximussto.data.database.entity.RequestDatabaseEntity
import com.example.maximussto.data.database.gateway.RequestGateway
import com.example.maximussto.domain.entity.implementation.Request
import com.example.maximussto.domain.entity.implementation.RequestType
import com.example.maximussto.domain.mapper.RequestMapper
import com.example.maximussto.domain.repository.base.RequestDetails
import com.example.maximussto.domain.repository.base.RequestRepository

class RequestRepositoryImpl : RequestRepository {

    fun create(type: RequestType, text: String): Request {
        val order = Request(type = type, text = text)
        add(order)
        return order
    }

    override fun add(entity: Request) {
        RequestGateway.create(RequestMapper.transform(entity))
    }

    override fun readAll(): List<Request> {
        return RequestGateway.readAll().map {
            return@map RequestMapper.transform(it, readOrderDetails(it))
        }.sortedBy { it.id }
    }

    override fun read(id: String): Request? {
        val order = RequestGateway.read(id) ?: return null
        return RequestMapper.transform(order, readOrderDetails(order))
    }

    override fun delete(entity: Request) {
        RequestGateway.delete(entity.id)
    }

    override fun update(entity: Request) {
        delete(entity)
        add(entity)
    }

    private fun readOrderDetails(request: RequestDatabaseEntity): RequestDetails {
        val operator = request.managerId?.let { ManagerRepositoryImpl().read(it) }
        val courier = request.masterId?.let { MasterRepositoryImpl().read(it) }
        return RequestDetails(operator, courier)
    }
}