package com.example.maximussto.service.usecase.client

import com.example.maximussto.domain.entity.implementation.RequestType
import com.example.maximussto.domain.repository.implementation.RequestRepositoryImpl


class RequestCreation {
    fun createRequest(text: String, type: RequestType, block: ((success: Boolean) -> Unit)? = null) {
        try {
            RequestRepositoryImpl().create(type, text)
            block?.invoke(true)
        } catch (e: Exception) {
            block?.invoke(false)
        }
    }
}