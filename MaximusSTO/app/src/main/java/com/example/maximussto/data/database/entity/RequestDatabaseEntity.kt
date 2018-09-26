package com.example.maximussto.data.database.entity

data class RequestDatabaseEntity(
    override val id: String,
    val status: Int,
    val type: Int,
    val text: String,
    val managerId: String? = null,
    val masterId: String? = null
) : DatabaseEntity