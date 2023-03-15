package com.example.studentlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_update_delete_gadget.*
import java.util.*

class UpdateDeleteGadget : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_delete_gadget)

        val gadgetName = intent.getStringExtra("NAME")
        val gadgetId = UUID.fromString(intent.getStringExtra("ID"))
        updateNameText.setText(gadgetName)

        updateToDatabaseButton.setOnClickListener {
            val g = Gadget(gadgetId, updateNameText.text.toString())
            GadgetRepository.get().updateGadget(g)
            finish()
        }

        deleteButton.setOnClickListener {
            GadgetRepository.get().deleteGadget(gadgetId)
            finish()
        }
    }
}