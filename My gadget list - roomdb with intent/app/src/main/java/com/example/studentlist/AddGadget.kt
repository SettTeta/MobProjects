package com.example.studentlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_gadget.*

class AddGadget : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_gadget)

        addToDatabaseButton.setOnClickListener {
            val g = Gadget()
            g.name = insertNameText.text.toString()

            GadgetRepository.get().addGadget(g)
            finish()
        }
    }
}