package com.example.maximussto.domain.mapper

import com.example.maximussto.data.database.entity.ClientDatabaseEntity
import com.example.maximussto.domain.entity.implementation.Client

object ClientMapper : Mapper<Client, ClientDatabaseEntity> {
    override fun transform(data: Client) = ClientDatabaseEntity(
        id = data.id,
        name = data.name,
        login = data.login,
        password = data.password
    )

    override fun transform(data: ClientDatabaseEntity) = Client(
        id = data.id,
        name = data.name,
        login = data.login,
        password = data.password
    )

    override fun transform(collection: Collection<ClientDatabaseEntity>) =
        collection.map { transform(it) }
}