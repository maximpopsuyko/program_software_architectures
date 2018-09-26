package com.example.maximussto.domain.mapper

import com.example.maximussto.data.database.entity.MasterDatabaseEntity
import com.example.maximussto.domain.entity.implementation.Master

object MasterMapper : Mapper<Master, MasterDatabaseEntity> {
    override fun transform(data: Master) = MasterDatabaseEntity(
        id = data.id,
        name = data.name,
        login = data.login,
        password = data.password
    )

    override fun transform(data: MasterDatabaseEntity) = Master(
        id = data.id,
        name = data.name,
        login = data.login,
        password = data.password
    )

    override fun transform(collection: Collection<MasterDatabaseEntity>) =
        collection.map { transform(it) }
}