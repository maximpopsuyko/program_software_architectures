package com.example.maximussto.data.database.gateway

import android.content.ContentValues
import com.example.maximussto.App
import com.example.maximussto.data.database.DatabaseManager
import com.example.maximussto.data.database.entity.RequestDatabaseEntity
import com.example.maximussto.data.database.table.RequestTable

object RequestGateway : Gateway<RequestDatabaseEntity> {
    override fun create(entity: RequestDatabaseEntity): Int {
        val db = DatabaseManager(App.context).writableDatabase
        val values = ContentValues().apply {
            put(RequestTable.id, entity.id)
            put(RequestTable.status, entity.status)
            put(RequestTable.type, entity.type)
            put(RequestTable.text, entity.text)
            put(RequestTable.managerId, entity.managerId)
            put(RequestTable.masterId, entity.masterId)
        }
        return db.insert(RequestTable.tableName, null, values).toInt()
    }

    override fun read(id: String): RequestDatabaseEntity? {
        val db = DatabaseManager(App.context).readableDatabase
        val projection = arrayOf(
            RequestTable.id, RequestTable.status, RequestTable.managerId, RequestTable.masterId,
            RequestTable.type, RequestTable.text
        )
        val selection = "${RequestTable.id} = ?"
        val selectionArgs = arrayOf(id)
        val cursor = db.query(
            RequestTable.tableName, projection, selection, selectionArgs,
            null, null, null
        )
        with(cursor) {
            return when {
                moveToNext() -> RequestDatabaseEntity(
                    id = getString(getColumnIndexOrThrow(RequestTable.id)),
                    status = getInt(getColumnIndexOrThrow(RequestTable.status)),
                    type = getInt(getColumnIndexOrThrow(RequestTable.type)),
                    text = getString(getColumnIndexOrThrow(RequestTable.text)),
                    managerId = getString(getColumnIndexOrThrow(RequestTable.managerId)),
                    masterId = getString(getColumnIndexOrThrow(RequestTable.masterId))
                )
                else -> null
            }
        }
    }

    override fun update(entity: RequestDatabaseEntity) {
        val db = DatabaseManager(App.context).writableDatabase
        val values = ContentValues().apply {
            put(RequestTable.id, entity.id)
            put(RequestTable.status, entity.status)
            put(RequestTable.type, entity.type)
            put(RequestTable.text, entity.text)
            put(RequestTable.managerId, entity.managerId)
            put(RequestTable.masterId, entity.masterId)
        }
        val selection = "${RequestTable.id} LIKE ?"
        val selectionArgs = arrayOf(entity.id)
        db.update(RequestTable.tableName, values, selection, selectionArgs)
    }

    override fun delete(id: String) {
        val db = DatabaseManager(App.context).writableDatabase
        val selection = "${RequestTable.id} LIKE ?"
        val selectionArgs = arrayOf(id)
        db.delete(RequestTable.tableName, selection, selectionArgs)
    }

    override fun readAll(): List<RequestDatabaseEntity> {
        val db = DatabaseManager(App.context).readableDatabase
        val projection = arrayOf(
            RequestTable.id, RequestTable.status, RequestTable.managerId, RequestTable.masterId,
            RequestTable.type, RequestTable.text
        )
        val cursor = db.query(
            RequestTable.tableName, projection,
            null, null, null, null, null
        )
        val results = mutableListOf<RequestDatabaseEntity>()
        with(cursor) {
            while (moveToNext()) {
                results.add(
                    RequestDatabaseEntity(
                        id = getString(getColumnIndexOrThrow(RequestTable.id)),
                        status = getInt(getColumnIndexOrThrow(RequestTable.status)),
                        type = getInt(getColumnIndexOrThrow(RequestTable.type)),
                        text = getString(getColumnIndexOrThrow(RequestTable.text)),
                        managerId = getString(getColumnIndexOrThrow(RequestTable.managerId)),
                        masterId = getString(getColumnIndexOrThrow(RequestTable.masterId))
                    )
                )
            }
        }
        return results
    }
}