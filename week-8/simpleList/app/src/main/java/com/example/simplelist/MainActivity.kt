package com.example.simplelist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.sqrt

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var inputNumber: EditText

    val items = mutableListOf<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        inputNumber = findViewById(R.id.positive_integer)
        inputNumber.addTextChangedListener(numberTextWatcher)

        findViewById<Button>(R.id.even_number).setOnClickListener(this)
        findViewById<Button>(R.id.odd_number).setOnClickListener(this)
        findViewById<Button>(R.id.square_number).setOnClickListener(this)


//        val results = findViewById<ListView>(R.id.result)
//        val adapter: ArrayAdapter<Int> = ArrayAdapter(
//            this,
//            android.R.layout.simple_list_item_1,
//            items
//        )
//        results.adapter = adapter

    }

    override fun onClick(p0: View?) {
        items.clear()
        val n: Int = inputNumber.text.toString().toInt()
        val id = p0?.id
        if (id == R.id.even_number) {
            for (i in 0..n) {
                if (i % 2 == 0)
                    items.add(i)
            }
        }
        else if (id == R.id.odd_number) {
            for (i in 0..n) {
                if (i % 2 != 0)
                    items.add(i)
            }
        }
        else if (id == R.id.square_number) {
            for (i in 0..n) {
                if (sqrt(i.toDouble()).toInt() * sqrt(i.toDouble()).toInt() == i)
                    items.add(i)
            }
        }
        val results = findViewById<ListView>(R.id.result)
        val adapter: ArrayAdapter<Int> = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            items
        )
        results.adapter = adapter
    }

    private val numberTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) {}
    }

}