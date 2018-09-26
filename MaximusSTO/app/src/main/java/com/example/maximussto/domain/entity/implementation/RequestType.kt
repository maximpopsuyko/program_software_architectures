package com.example.maximussto.domain.entity.implementation

enum class RequestType(val str: String) {
    MAINTENANCE("Техническое обслуживание"),
    GUARANTEE("Гарантийный ремонт"),
    REPAIRS("Обычный ремонт")
}