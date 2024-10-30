package com.example.gmailphake

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val emailList = listOf(
            Email("A", "ĐJĐJD", "5 mins"),
            Email("B", "ABABCD", "09:15 AM"),
            Email("C", "SKXK", "08:45 AM"),
            Email("D", "SMMX", "Yesterday"),
            Email("E", "MMM?", "Yesterday"),
            Email("F", "ẤUEU summary", "Monday") )
        val adapter = EmailAdapter(emailList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}