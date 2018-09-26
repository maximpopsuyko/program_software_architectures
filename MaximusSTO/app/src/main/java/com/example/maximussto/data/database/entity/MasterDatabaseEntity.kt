package com.example.maximussto.data.database.entity

data class MasterDatabaseEntity(
    override val id: String,
    val login: String,
    val password: String,
    val name: String
) : DatabaseEntity