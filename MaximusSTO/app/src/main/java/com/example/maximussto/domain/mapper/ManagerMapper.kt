package com.example.maximussto.domain.mapper

import com.example.maximussto.data.database.entity.ManagerDatabaseEntity
import com.example.maximussto.domain.entity.implementation.Manager

object ManagerMapper : Mapper<Manager, ManagerDatabaseEntity> {
    override fun transform(data: Manager) = ManagerDatabaseEntity(
        id = data.id,
        name = data.name,
        login = data.login,
        password = data.password
    )

    override fun transform(data: ManagerDatabaseEntity) = Manager(
        id = data.id,
        name = data.name,
        login = data.login,
        password = data.password
    )

    override fun transform(collection: Collection<ManagerDatabaseEntity>) =
        collection.map { transform(it) }
}