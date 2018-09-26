package com.example.maximussto.data.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.maximussto.data.database.table.ClientTable
import com.example.maximussto.data.database.table.MasterTable
import com.example.maximussto.data.database.table.ManagerTable
import com.example.maximussto.data.database.table.RequestTable

class DatabaseManager(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db ?: return

        db.execSQL(ClientTable.createTableSql)
        db.execSQL(MasterTable.createTableSql)
        db.execSQL(ManagerTable.createTableSql)
        db.execSQL(RequestTable.createTableSql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db ?: return

        db.execSQL(ClientTable.dropTableSql)
        db.execSQL(MasterTable.dropTableSql)
        db.execSQL(ManagerTable.dropTableSql)
        db.execSQL(RequestTable.dropTableSql)

        onCreate(db)
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "sushi.db"
    }
}