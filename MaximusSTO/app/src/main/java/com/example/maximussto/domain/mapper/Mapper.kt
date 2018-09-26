package com.example.maximussto.domain.mapper

import com.example.maximussto.data.database.entity.DatabaseEntity
import com.example.maximussto.domain.entity.base.Entity

interface Mapper<T : Entity, R : DatabaseEntity> {
    fun transform(data: T): R

    fun transform(data: R): T

    fun transform(collection: Collection<R>): Collection<T>
}