package com.example.maximussto.service.usecase

import com.example.maximussto.domain.repository.implementation.ClientRepositoryImpl
import com.example.maximussto.domain.repository.implementation.ManagerRepositoryImpl
import com.example.maximussto.domain.repository.implementation.MasterRepositoryImpl

class Authorization {
    fun registerClient(name: String, login: String, password: String) =
            ClientRepositoryImpl().create(name, login, password)

    fun registerManager(name: String, login: String, password: String) =
            ManagerRepositoryImpl().create(name, login, password)

    fun registerMaster(name: String, login: String, password: String) =
            MasterRepositoryImpl().create(name, login, password)

    fun loginClient(login: String, password: String) =
            ClientRepositoryImpl().readAll().find { it.login == login && it.password == password }

    fun loginManager(login: String, password: String) =
            ManagerRepositoryImpl().readAll().find { it.login == login && it.password == password }

    fun loginMaster(login: String, password: String) =
            MasterRepositoryImpl().readAll().find { it.login == login && it.password == password }
}