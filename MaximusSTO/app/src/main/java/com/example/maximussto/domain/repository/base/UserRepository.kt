package com.example.maximussto.domain.repository.base

import com.example.maximussto.domain.entity.base.UserEntity

interface UserRepository<T : UserEntity> : Repository<T>