package com.example.maximussto.domain.entity.base

import com.example.maximussto.domain.Util

interface Entity {
    val id: String

    companion object {
        fun generateId() = Util.generateID()
    }
}