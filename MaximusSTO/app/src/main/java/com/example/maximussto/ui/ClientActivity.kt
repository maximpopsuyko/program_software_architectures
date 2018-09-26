package com.example.maximussto.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.maximussto.R
import com.example.maximussto.domain.entity.implementation.RequestType
import com.example.maximussto.service.usecase.client.RequestCreation
import kotlinx.android.synthetic.main.activity_client.*

class ClientActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client)

        checkbox_maintenance.setOnClickListener {
            checkbox_guarantee.isChecked = false
            checkbox_repairs.isChecked = false
        }

        checkbox_guarantee.setOnClickListener {
            checkbox_maintenance.isChecked = false
            checkbox_repairs.isChecked = false
        }

        checkbox_repairs.setOnClickListener {
            checkbox_guarantee.isChecked = false
            checkbox_maintenance.isChecked = false
        }

        create_request_button.setOnClickListener {
            val text = request_text.text.toString()
            val type = when {
                checkbox_maintenance.isChecked -> RequestType.MAINTENANCE
                checkbox_guarantee.isChecked -> RequestType.GUARANTEE
                checkbox_repairs.isChecked -> RequestType.REPAIRS
                else -> {
                    Toast.makeText(this, "Тип не выбран", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
            }
            RequestCreation().createRequest(text, type) { isSuccess ->
                when {
                    isSuccess -> Toast.makeText(this, "Запрос создан", Toast.LENGTH_LONG).show()
                    else -> Toast.makeText(this, "Ошибка", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
