package com.example.maximussto.domain.entity.implementation

import com.example.maximussto.domain.entity.base.Entity
import com.example.maximussto.domain.entity.base.UserEntity


data class Master(
    override val id: String = Entity.generateId(),
    override val login: String,
    override val password: String,
    override val name: String
) : UserEntity