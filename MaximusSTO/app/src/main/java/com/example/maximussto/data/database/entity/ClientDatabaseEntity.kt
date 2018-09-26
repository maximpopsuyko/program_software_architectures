package com.example.maximussto.data.database.entity

data class ClientDatabaseEntity(
    override val id: String,
    val login: String,
    val password: String,
    val name: String
) : DatabaseEntity