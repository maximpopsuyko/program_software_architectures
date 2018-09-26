package com.example.maximussto.domain.repository.implementation

import com.example.maximussto.data.database.gateway.ManagerGateway
import com.example.maximussto.domain.entity.implementation.Manager
import com.example.maximussto.domain.mapper.ManagerMapper
import com.example.maximussto.domain.repository.base.ManagerRepository

class ManagerRepositoryImpl : ManagerRepository {
    fun create(name: String, login: String, password: String): String {
        val entity = Manager(name = name, login = login, password = password)
        add(entity)
        return entity.id
    }

    override fun add(entity: Manager) {
        ManagerGateway.create(ManagerMapper.transform(entity))
    }

    override fun readAll(): List<Manager> {
        return ManagerMapper.transform(ManagerGateway.readAll()).toList()
    }

    override fun read(id: String): Manager? {
        return ManagerGateway.read(id)?.let { ManagerMapper.transform(it) }
    }

    override fun delete(entity: Manager) {
        ManagerGateway.delete(entity.id)
    }

    override fun update(entity: Manager) {
        ManagerGateway.update(ManagerMapper.transform(entity))
    }
}