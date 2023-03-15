package com.example.studentlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_item.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        goToAddPageButton.setOnClickListener {
            val intent = Intent(this, AddGadget::class.java)
            startActivity(intent)
        }

        listView.layoutManager = LinearLayoutManager(this)
        getGadgetFromDatabaseToListView()
    }

    override fun onResume() {
        super.onResume()
        getGadgetFromDatabaseToListView()
    }

    private fun getGadgetFromDatabaseToListView() {
        GadgetRepository.get().getGadgets().observe(this) { gadgets ->
            gadgets.let {
                listView.adapter = GadgetAdapter(gadgets)

            }
        }
    }


    inner class GadgetHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        val gadgetName = itemView.gadgetName
        var gadgetId = ""

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val intent = Intent(p0!!.context, UpdateDeleteGadget::class.java)
            intent.putExtra("NAME", gadgetName.text)
            intent.putExtra("ID", gadgetId)

            startActivity(intent)
        }
    }

    inner class GadgetAdapter(var gadgets: List<Gadget>): RecyclerView.Adapter<GadgetHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GadgetHolder {
            val view = layoutInflater.inflate(R.layout.row_item, parent, false)
            return GadgetHolder(view)
        }

        override fun onBindViewHolder(holder: GadgetHolder, position: Int) {
            holder.gadgetName.text = gadgets[position].name
            holder.gadgetId = gadgets[position].id.toString()
        }

        override fun getItemCount(): Int {
            return gadgets.size
        }
    }

}