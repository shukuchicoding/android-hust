package com.example.horizontalscroolview

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val txtMsg: TextView = findViewById(R.id.txtMsg)
        val items: Array<String> = arrayOf("Data-0", "Data-1", "Data-2", "Data-3",
            "Data-4", "Data-5", "Data-6", "Data-7")
        val adapter: ArrayAdapter<String> = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            items)
        val listView: ListView = findViewById(R.id.my_list)
        listView.adapter = adapter
        listView.setOnItemClickListener { adapterView, view, i, l -> txtMsg.text = "Position: $i\nData: ${items[i]}"}
    }
}