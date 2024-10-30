package com.example.listexample1

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.GridView
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val items: Array<String> = arrayOf(
            "Data-0", "Data-1", "Data-2", "Data-3",
            "Data-4", "Data-5", "Data-6", "Data-7"
        )
//        AdapterNotifyChanged-------------
//        val items = mutableListOf<String>()
//        repeat(50) {
//            items.add("Item $it")
//        }
        val adapter: ArrayAdapter<String> = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            items
        )
//        ListView-------------------------
//        val listView: ListView = findViewById<ListView>(R.id.list_view)
//        listView.adapter = adapter
//
//        listView.setOnItemClickListener { adapterView, view, i, l ->
//            val selectedItem = items[i]
//            Log.v("TAG", "Selected: $selectedItem")
//        }
//        AdapterNotifyChanged-------------
//        findViewById<Button>(R.id.btn_add).setOnClickListener {
//            items.add(0, "NEW ITEM")
//            adapter.notifyDataSetChanged()
//        }
//        findViewById<Button>(R.id.btn_remove).setOnClickListener {
//            items.removeAt(0)
//            adapter.notifyDataSetChanged()
//        }
//        findViewById<Button>(R.id.btn_update).setOnClickListener {
//            items[0] = "UPDATED ITEM"
//            adapter.notifyDataSetChanged()
//        }

//        Spinner--------------------------
        val spinner1 = findViewById<Spinner>(R.id.spinner1)
        spinner1.adapter = adapter

        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                val selectedItem = p0.getItemAtPosition(p2).toString()
                val selectedItem = items[p2]
                Log.v("MainActivity", "Selected item: $selectedItem")
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                Log.v("MainActivity", "Nothing selected")
            }
        }
//        GridView-------------------------
//        val gridview = findViewById<GridView>(R.id.grid_view)
//        gridview.adapter = adapter
//
//        gridview.setOnItemClickListener { adapterView, view, i, l ->
//            val selectedItem = items[i]
//            Log.v("TAG", "Selected: $selectedItem")
//        }
//        AutoCompleView-------------------
//        val items_autoEdit: Array<String> = arrayOf(
//            "words", "starting", "with", "set",
//            "Setback", "Setline", "Setoffs", "Setouts", "Setters", "Setting",
//            "Settled", "Settler", "Wordless", "Wordiness", "Adios"
//        )
//        val adapter_autoEdit: ArrayAdapter<String> = ArrayAdapter(
//            this,
//            android.R.layout.simple_list_item_1,
//            items_autoEdit
//        )
//
//        val autoEdit = findViewById<AutoCompleteTextView>(R.id.auto_edit)
//        autoEdit.setAdapter(adapter_autoEdit)
    }
}