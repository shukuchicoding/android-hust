package com.example.calculator

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var result: TextView

    var state: Int = 1
    var op: Int = 0
    var op1: Int = 0
    var op2: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        result = findViewById(R.id.tvResult)

        findViewById<Button>(R.id.btn0).setOnClickListener(this)
        findViewById<Button>(R.id.btn1).setOnClickListener(this)
        findViewById<Button>(R.id.btn2).setOnClickListener(this)
        findViewById<Button>(R.id.btn3).setOnClickListener(this)
        findViewById<Button>(R.id.btn4).setOnClickListener(this)
        findViewById<Button>(R.id.btn5).setOnClickListener(this)
        findViewById<Button>(R.id.btn6).setOnClickListener(this)
        findViewById<Button>(R.id.btn7).setOnClickListener(this)
        findViewById<Button>(R.id.btn8).setOnClickListener(this)
        findViewById<Button>(R.id.btn9).setOnClickListener(this)

        findViewById<Button>(R.id.btnAdd).setOnClickListener(this)
        findViewById<Button>(R.id.btnSubtract).setOnClickListener(this)
        findViewById<Button>(R.id.btnMultiply).setOnClickListener(this)
        findViewById<Button>(R.id.btnDivide).setOnClickListener(this)

        findViewById<Button>(R.id.btnEquals).setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val id = p0?.id
        if (id == R.id.btn0) {
            addDigit(0)
        } else if (id == R.id.btn1) {
            addDigit(1)
        } else if (id == R.id.btn2) {
            addDigit(2)
        } else if (id == R.id.btn3) {
            addDigit(3)
        } else if (id == R.id.btn4) {
            addDigit(4)
        } else if (id == R.id.btn5) {
            addDigit(5)
        } else if (id == R.id.btn6) {
            addDigit(6)
        } else if (id == R.id.btn7) {
            addDigit(7)
        } else if (id == R.id.btn8) {
            addDigit(8)
        } else if (id == R.id.btn9) {
            addDigit(9)
        } else if (id == R.id.btnAdd) {
            op = 1
            state = 2
        } else if (id == R.id.btnSubtract) {
            op = 2
            state = 2
        } else if (id == R.id.btnMultiply) {
            op = 3
            state = 2
        } else if (id == R.id.btnDivide) {
            op = 4
            state = 2
        } else if (id == R.id.btnEquals) {
            var ans = 0
            if (op == 1) {
                ans = op1 + op2
            } else if (op == 2) {
                ans = op1 - op2
            } else if (op == 3) {
                ans = op1 * op2
            } else if (op == 4) {
                ans = op1 / op2
            }
            result.text = "$ans"
            state = 1
            op1 = 0
            op2 = 0
            op = 0
        }
    }

    fun addDigit(c: Int) {
        if (state == 1) {
            op1 = op1 * 10 + c
            result.text = "$op1"
        } else {
            op2 = op2 * 10 + c
            result.text = "$op2"
        }
    }
}