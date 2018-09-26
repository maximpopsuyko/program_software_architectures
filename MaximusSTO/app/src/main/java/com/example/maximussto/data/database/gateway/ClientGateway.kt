package com.example.maximussto.data.database.gateway

import android.content.ContentValues
import com.example.maximussto.App
import com.example.maximussto.data.database.DatabaseManager
import com.example.maximussto.data.database.entity.ClientDatabaseEntity
import com.example.maximussto.data.database.table.ClientTable

object ClientGateway : Gateway<ClientDatabaseEntity> {
    override fun create(entity: ClientDatabaseEntity): Int {
        val db = DatabaseManager(App.context).writableDatabase
        val values = ContentValues().apply {
            put(ClientTable.id, entity.id)
            put(ClientTable.name, entity.name)
            put(ClientTable.login, entity.login)
            put(ClientTable.password, entity.password)
        }
        return db.insert(ClientTable.tableName, null, values).toInt()
    }

    override fun read(id: String): ClientDatabaseEntity? {
        val db = DatabaseManager(App.context).readableDatabase
        val projection = arrayOf(
            ClientTable.id, ClientTable.name, ClientTable.login, ClientTable.password
        )
        val selection = "${ClientTable.id} = ?"
        val selectionArgs = arrayOf(id)
        val cursor = db.query(
            ClientTable.tableName, projection, selection, selectionArgs,
            null, null, null
        )
        with(cursor) {
            return when {
                moveToNext() -> ClientDatabaseEntity(
                    id = getString(getColumnIndexOrThrow(ClientTable.id)),
                    name = getString(getColumnIndexOrThrow(ClientTable.name)),
                    login = getString(getColumnIndexOrThrow(ClientTable.login)),
                    password = getString(getColumnIndexOrThrow(ClientTable.password))
                )
                else -> null
            }
        }
    }

    override fun update(entity: ClientDatabaseEntity) {
        val db = DatabaseManager(App.context).writableDatabase
        val values = ContentValues().apply {
            put(ClientTable.id, entity.id)
            put(ClientTable.name, entity.name)
            put(ClientTable.login, entity.login)
            put(ClientTable.password, entity.password)
        }
        val selection = "${ClientTable.id} LIKE ?"
        val selectionArgs = arrayOf(entity.id)
        db.update(ClientTable.tableName, values, selection, selectionArgs)
    }

    override fun delete(id: String) {
        val db = DatabaseManager(App.context).writableDatabase
        val selection = "${ClientTable.id} LIKE ?"
        val selectionArgs = arrayOf(id)
        db.delete(ClientTable.tableName, selection, selectionArgs)
    }

    override fun readAll(): List<ClientDatabaseEntity> {
        val db = DatabaseManager(App.context).readableDatabase
        val projection = arrayOf(
            ClientTable.id, ClientTable.name, ClientTable.login, ClientTable.password
        )
        val cursor = db.query(
            ClientTable.tableName, projection,
            null, null, null, null, null
        )
        val results = mutableListOf<ClientDatabaseEntity>()
        with(cursor) {
            while (moveToNext()) {
                results.add(
                    ClientDatabaseEntity(
                        id = getString(getColumnIndexOrThrow(ClientTable.id)),
                        name = getString(getColumnIndexOrThrow(ClientTable.name)),
                        login = getString(getColumnIndexOrThrow(ClientTable.login)),
                        password = getString(getColumnIndexOrThrow(ClientTable.password))
                    )
                )
            }
        }
        return results
    }
}