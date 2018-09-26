package com.example.maximussto.data.database.gateway

import android.content.ContentValues
import com.example.maximussto.App
import com.example.maximussto.data.database.DatabaseManager
import com.example.maximussto.data.database.entity.ManagerDatabaseEntity
import com.example.maximussto.data.database.table.ManagerTable

object ManagerGateway : Gateway<ManagerDatabaseEntity> {
    override fun create(entity: ManagerDatabaseEntity): Int {
        val db = DatabaseManager(App.context).writableDatabase
        val values = ContentValues().apply {
            put(ManagerTable.id, entity.id)
            put(ManagerTable.name, entity.name)
            put(ManagerTable.login, entity.login)
            put(ManagerTable.password, entity.password)
        }
        return db.insert(ManagerTable.tableName, null, values).toInt()
    }

    override fun read(id: String): ManagerDatabaseEntity? {
        val db = DatabaseManager(App.context).readableDatabase
        val projection = arrayOf(
            ManagerTable.id, ManagerTable.name, ManagerTable.login, ManagerTable.password
        )
        val selection = "${ManagerTable.id} = ?"
        val selectionArgs = arrayOf(id)
        val cursor = db.query(
            ManagerTable.tableName, projection, selection, selectionArgs,
            null, null, null
        )
        with(cursor) {
            return when {
                moveToNext() -> ManagerDatabaseEntity(
                    id = getString(getColumnIndexOrThrow(ManagerTable.id)),
                    name = getString(getColumnIndexOrThrow(ManagerTable.name)),
                    login = getString(getColumnIndexOrThrow(ManagerTable.login)),
                    password = getString(getColumnIndexOrThrow(ManagerTable.password))
                )
                else -> null
            }
        }
    }

    override fun update(entity: ManagerDatabaseEntity) {
        val db = DatabaseManager(App.context).writableDatabase
        val values = ContentValues().apply {
            put(ManagerTable.id, entity.id)
            put(ManagerTable.name, entity.name)
            put(ManagerTable.login, entity.login)
            put(ManagerTable.password, entity.password)
        }
        val selection = "${ManagerTable.id} LIKE ?"
        val selectionArgs = arrayOf(entity.id)
        db.update(ManagerTable.tableName, values, selection, selectionArgs)
    }

    override fun delete(id: String) {
        val db = DatabaseManager(App.context).writableDatabase
        val selection = "${ManagerTable.id} LIKE ?"
        val selectionArgs = arrayOf(id)
        db.delete(ManagerTable.tableName, selection, selectionArgs)
    }

    override fun readAll(): List<ManagerDatabaseEntity> {
        val db = DatabaseManager(App.context).readableDatabase
        val projection = arrayOf(
            ManagerTable.id, ManagerTable.name, ManagerTable.login, ManagerTable.password
        )
        val cursor = db.query(
            ManagerTable.tableName, projection,
            null, null, null, null, null
        )
        val results = mutableListOf<ManagerDatabaseEntity>()
        with(cursor) {
            while (moveToNext()) {
                results.add(
                    ManagerDatabaseEntity(
                        id = getString(getColumnIndexOrThrow(ManagerTable.id)),
                        name = getString(getColumnIndexOrThrow(ManagerTable.name)),
                        login = getString(getColumnIndexOrThrow(ManagerTable.login)),
                        password = getString(getColumnIndexOrThrow(ManagerTable.password))
                    )
                )
            }
        }
        return results
    }
}