package com.example.maximussto.data.database.gateway

import com.example.maximussto.data.database.entity.DatabaseEntity

interface Gateway<T : DatabaseEntity> {
    fun create(entity: T): Int

    fun read(id: String): T?

    fun update(entity: T)

    fun delete(id: String)

    fun readAll(): List<T>
}