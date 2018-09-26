package com.example.maximussto.domain.repository.implementation

import com.example.maximussto.data.database.gateway.MasterGateway
import com.example.maximussto.domain.entity.implementation.Master
import com.example.maximussto.domain.mapper.MasterMapper
import com.example.maximussto.domain.repository.base.MasterRepository

class MasterRepositoryImpl : MasterRepository {
    fun create(name: String, login: String, password: String): String {
        val entity = Master(name = name, login = login, password = password)
        add(entity)
        return entity.id
    }

    override fun add(entity: Master) {
        MasterGateway.create(MasterMapper.transform(entity))
    }

    override fun readAll(): List<Master> {
        return MasterMapper.transform(MasterGateway.readAll()).toList()
    }

    override fun read(id: String): Master? {
        return MasterGateway.read(id)?.let { MasterMapper.transform(it) }
    }

    override fun delete(entity: Master) {
        MasterGateway.delete(entity.id)
    }

    override fun update(entity: Master) {
        MasterGateway.update(MasterMapper.transform(entity))
    }
}