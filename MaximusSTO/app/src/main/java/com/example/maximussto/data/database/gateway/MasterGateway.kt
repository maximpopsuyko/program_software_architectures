package com.example.maximussto.data.database.gateway

import android.content.ContentValues
import com.example.maximussto.App
import com.example.maximussto.data.database.DatabaseManager
import com.example.maximussto.data.database.entity.MasterDatabaseEntity
import com.example.maximussto.data.database.table.MasterTable

object MasterGateway : Gateway<MasterDatabaseEntity> {
    override fun create(entity: MasterDatabaseEntity): Int {
        val db = DatabaseManager(App.context).writableDatabase
        val values = ContentValues().apply {
            put(MasterTable.id, entity.id)
            put(MasterTable.name, entity.name)
            put(MasterTable.login, entity.login)
            put(MasterTable.password, entity.password)
        }
        return db.insert(MasterTable.tableName, null, values).toInt()
    }

    override fun read(id: String): MasterDatabaseEntity? {
        val db = DatabaseManager(App.context).readableDatabase
        val projection = arrayOf(
            MasterTable.id, MasterTable.name, MasterTable.login, MasterTable.password
        )
        val selection = "${MasterTable.id} = ?"
        val selectionArgs = arrayOf(id)
        val cursor = db.query(
            MasterTable.tableName, projection, selection, selectionArgs,
            null, null, null
        )
        with(cursor) {
            return when {
                moveToNext() -> MasterDatabaseEntity(
                    id = getString(getColumnIndexOrThrow(MasterTable.id)),
                    name = getString(getColumnIndexOrThrow(MasterTable.name)),
                    login = getString(getColumnIndexOrThrow(MasterTable.login)),
                    password = getString(getColumnIndexOrThrow(MasterTable.password))
                )
                else -> null
            }
        }
    }

    override fun update(entity: MasterDatabaseEntity) {
        val db = DatabaseManager(App.context).writableDatabase
        val values = ContentValues().apply {
            put(MasterTable.id, entity.id)
            put(MasterTable.name, entity.name)
            put(MasterTable.login, entity.login)
            put(MasterTable.password, entity.password)
        }
        val selection = "${MasterTable.id} LIKE ?"
        val selectionArgs = arrayOf(entity.id)
        db.update(MasterTable.tableName, values, selection, selectionArgs)
    }

    override fun delete(id: String) {
        val db = DatabaseManager(App.context).writableDatabase
        val selection = "${MasterTable.id} LIKE ?"
        val selectionArgs = arrayOf(id)
        db.delete(MasterTable.tableName, selection, selectionArgs)
    }

    override fun readAll(): List<MasterDatabaseEntity> {
        val db = DatabaseManager(App.context).readableDatabase
        val projection = arrayOf(
            MasterTable.id, MasterTable.name, MasterTable.login, MasterTable.password
        )
        val cursor = db.query(
            MasterTable.tableName, projection,
            null, null, null, null, null
        )
        val results = mutableListOf<MasterDatabaseEntity>()
        with(cursor) {
            while (moveToNext()) {
                results.add(
                    MasterDatabaseEntity(
                        id = getString(getColumnIndexOrThrow(MasterTable.id)),
                        name = getString(getColumnIndexOrThrow(MasterTable.name)),
                        login = getString(getColumnIndexOrThrow(MasterTable.login)),
                        password = getString(getColumnIndexOrThrow(MasterTable.password))
                    )
                )
            }
        }
        return results
    }
}