package com.example.maximussto.domain.repository.implementation

import com.example.maximussto.data.database.gateway.ClientGateway
import com.example.maximussto.domain.entity.implementation.Client
import com.example.maximussto.domain.mapper.ClientMapper
import com.example.maximussto.domain.repository.base.ClientRepository

class ClientRepositoryImpl : ClientRepository {
    fun create(name: String, login: String, password: String): String {
        val entity = Client(name = name, login = login, password = password)
        add(entity)
        return entity.id
    }

    override fun add(entity: Client) {
        ClientGateway.create(ClientMapper.transform(entity))
    }

    override fun readAll(): List<Client> {
        return ClientMapper.transform(ClientGateway.readAll()).toList()
    }

    override fun read(id: String): Client? {
        return ClientGateway.read(id)?.let { ClientMapper.transform(it) }
    }

    override fun delete(entity: Client) {
        ClientGateway.delete(entity.id)
    }

    override fun update(entity: Client) {
        ClientGateway.update(ClientMapper.transform(entity))
    }
}