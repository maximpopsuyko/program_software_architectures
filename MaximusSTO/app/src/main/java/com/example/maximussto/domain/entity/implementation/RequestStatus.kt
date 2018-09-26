package com.example.maximussto.domain.entity.implementation

enum class RequestStatus(val str: String) {
    WAIT_CONFIRM("Ожидает подтверждения"),
    CONFIRMED("Подтверждено"),
    WAIT_REPAIRS("Ожидает ремонта"),
    IN_REPAIRS("В ремонте"),
    COMPLETE("Выполнен"),
    CANCELED("Отменен")
}