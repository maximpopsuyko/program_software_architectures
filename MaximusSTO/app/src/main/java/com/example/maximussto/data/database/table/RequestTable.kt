package com.example.maximussto.data.database.table

object RequestTable : Table {
    override val tableName = "requests"
    override val id = ID_COLUMN_NAME

    const val status = "status"
    const val managerId = "manager_id"
    const val masterId = "master_id"
    const val type = "type"
    const val text = "text"

    enum class Status(val code: Int) {
        WAIT_CONFIRM(0),
        CONFIRMED(1),
        WAIT_REPAIRS(2),
        IN_REPAIRS(3),
        COMPLETE(4),
        CANCELED(5);

        companion object {
            fun byCode(code: Int) = Status.values().find { it.code == code } ?: WAIT_CONFIRM
        }
    }

    enum class Type(val code: Int) {
        MAINTENANCE(0),
        GUARANTEE(1),
        REPAIRS(2);

        companion object {
            fun byCode(code: Int) = Type.values().find { it.code == code } ?: MAINTENANCE
        }
    }

    override val createTableSql: String
        get() = "CREATE TABLE $tableName ($id TEXT PRIMARY KEY, " +
                "$status INTEGER NOT NULL DEFAULT ${Status.WAIT_CONFIRM.code}, " +
                "$managerId TEXT, $masterId TEXT, " +
                "$type INTEGER NOT NULL, $text TEXT, " +
                "FOREIGN KEY($managerId) REFERENCES ${ManagerTable.tableName}(${ManagerTable.id}), " +
                "FOREIGN KEY($masterId) REFERENCES ${MasterTable.tableName}(${MasterTable.id}));"

    override val dropTableSql: String
        get() = "DROP TABLE IF EXISTS $tableName"
}