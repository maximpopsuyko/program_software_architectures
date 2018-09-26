package com.example.maximussto.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.maximussto.R
import com.example.maximussto.service.usecase.Authorization
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {

    private lateinit var type: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        title_text.text = intent.getStringExtra("title")
        type = intent.getStringExtra("title")

        registration_button.setOnClickListener {
            val name = registration_name.text.toString()
            val login = registration_login.text.toString()
            val password = registration_password.text.toString()
            val result = when (type) {
                "Клиент" -> Authorization().registerClient(name, login, password)
                "Менеджер" -> Authorization().registerManager(name, login, password)
                "Мастер" -> Authorization().registerMaster(name, login, password)
                else -> null
            }
            when {
                result != null -> Toast.makeText(this, "Всё ОК", Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show()
            }
        }

        login_button.setOnClickListener {
            val login = login_login.text.toString()
            val password = login_password.text.toString()
            val result = when (type) {
                "Клиент" -> Authorization().loginClient(login, password)
                "Менеджер" -> Authorization().loginManager(login, password)
                "Мастер" -> Authorization().loginMaster(login, password)
                else -> null
            }
            when {
                result != null -> startMainActivity(result.id)
                else -> Toast.makeText(this, "Что-то не так", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startMainActivity(userId: String) {
        when (type) {
            "Клиент" -> startActivity(Intent(this, ClientActivity::class.java))
            "Менеджер" -> startActivity(Intent(this, ManagerActivity::class.java))
            "Мастер" -> startActivity(Intent(this, MasterActivity::class.java).putExtra("master_id", userId))
        }
    }
}
