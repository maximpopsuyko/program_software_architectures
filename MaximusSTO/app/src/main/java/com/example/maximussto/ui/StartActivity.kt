package com.example.maximussto.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.maximussto.R
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        client_icon.setOnClickListener { startAuthActivity("Клиент") }
        manager_icon.setOnClickListener { startAuthActivity("Менеджер") }
        master_icon.setOnClickListener { startAuthActivity("Мастер") }
    }

    private fun startAuthActivity(title: String) {
        startActivity(Intent(this, AuthActivity::class.java).putExtra("title", title))
    }
}
