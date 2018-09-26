package com.example.maximussto.domain.repository.base

import com.example.maximussto.domain.entity.base.Entity

interface Repository<T : Entity> {
    fun add(entity: T)

    fun readAll(): List<T>

    fun read(id: String): T?

    fun delete(entity: T)

    fun update(entity: T)
}