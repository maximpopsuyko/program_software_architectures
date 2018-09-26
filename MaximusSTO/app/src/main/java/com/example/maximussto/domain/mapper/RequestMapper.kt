package com.example.maximussto.domain.mapper

import com.example.maximussto.data.database.entity.RequestDatabaseEntity
import com.example.maximussto.data.database.table.RequestTable
import com.example.maximussto.domain.entity.implementation.Request
import com.example.maximussto.domain.entity.implementation.RequestStatus
import com.example.maximussto.domain.entity.implementation.RequestType
import com.example.maximussto.domain.repository.base.RequestDetails

object RequestMapper : Mapper<Request, RequestDatabaseEntity> {
    override fun transform(data: Request) = RequestDatabaseEntity(
        id = data.id,
        managerId = data.manager?.id,
        type = data.type.transform().code,
        text = data.text,
        masterId = data.master?.id,
        status = data.status.transform().code
    )

    override fun transform(data: RequestDatabaseEntity) = throw Exception()

    fun transform(data: RequestDatabaseEntity, details: RequestDetails) = Request(
        id = data.id,
        text = data.text,
        type = RequestTable.Type.byCode(data.type).transform(),
        manager = details.first,
        master = details.second,
        status = RequestTable.Status.byCode(data.status).transform()
    )

    override fun transform(collection: Collection<RequestDatabaseEntity>) = throw Exception()

    private fun RequestStatus.transform() = when (this) {
        RequestStatus.WAIT_CONFIRM -> RequestTable.Status.WAIT_CONFIRM
        RequestStatus.CONFIRMED -> RequestTable.Status.CONFIRMED
        RequestStatus.WAIT_REPAIRS -> RequestTable.Status.WAIT_REPAIRS
        RequestStatus.IN_REPAIRS -> RequestTable.Status.IN_REPAIRS
        RequestStatus.COMPLETE -> RequestTable.Status.COMPLETE
        RequestStatus.CANCELED -> RequestTable.Status.CANCELED
    }

    private fun RequestTable.Status.transform() = when (this) {
        RequestTable.Status.WAIT_CONFIRM -> RequestStatus.WAIT_CONFIRM
        RequestTable.Status.CONFIRMED -> RequestStatus.CONFIRMED
        RequestTable.Status.WAIT_REPAIRS -> RequestStatus.WAIT_REPAIRS
        RequestTable.Status.IN_REPAIRS -> RequestStatus.IN_REPAIRS
        RequestTable.Status.COMPLETE -> RequestStatus.COMPLETE
        RequestTable.Status.CANCELED -> RequestStatus.CANCELED
    }

    private fun RequestType.transform() = when (this) {
        RequestType.MAINTENANCE -> RequestTable.Type.MAINTENANCE
        RequestType.GUARANTEE -> RequestTable.Type.GUARANTEE
        RequestType.REPAIRS -> RequestTable.Type.REPAIRS
    }

    private fun RequestTable.Type.transform() = when (this) {
        RequestTable.Type.MAINTENANCE -> RequestType.MAINTENANCE
        RequestTable.Type.GUARANTEE -> RequestType.GUARANTEE
        RequestTable.Type.REPAIRS -> RequestType.REPAIRS
    }
}