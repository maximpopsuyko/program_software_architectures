package com.example.maximussto.domain.repository.base

import com.example.maximussto.domain.entity.implementation.Master
import com.example.maximussto.domain.entity.implementation.Manager
import com.example.maximussto.domain.entity.implementation.Request

typealias RequestDetails = Pair<Manager?, Master?>

interface RequestRepository : Repository<Request>