package com.example.maximussto.domain.entity.implementation

import com.example.maximussto.domain.entity.base.Entity


data class Request(
    override val id: String = Entity.generateId(),
    var type: RequestType,
    val text: String,
    var status: RequestStatus = RequestStatus.WAIT_CONFIRM,
    var manager: Manager? = null,
    var master: Master? = null
) : Entity