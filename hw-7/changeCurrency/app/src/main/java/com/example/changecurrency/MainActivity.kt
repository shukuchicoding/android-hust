package com.example.changecurrency

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import java.text.DecimalFormat
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var amountEditText1: EditText
    lateinit var amountEditText2: EditText
    lateinit var currencySpinner1: Spinner
    lateinit var currencySpinner2: Spinner

    var updatingText = false
    var decimalFormat = DecimalFormat("#.##")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        amountEditText1 = findViewById(R.id.amountEditText1)
        amountEditText2 = findViewById(R.id.amountEditText2)
        currencySpinner1 = findViewById(R.id.currencySpinner1)
        currencySpinner2 = findViewById(R.id.currencySpinner2)

        val currencies = arrayOf("USD", "EUR", "CNY", "JPY", "VND")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        currencySpinner1.adapter = adapter
        currencySpinner2.adapter = adapter

        amountEditText1.addTextChangedListener(amountTextWatcher)
        amountEditText2.addTextChangedListener(amountTextWatcher)

        currencySpinner1.onItemSelectedListener = currencyItemSelectedListener
        currencySpinner2.onItemSelectedListener = currencyItemSelectedListener


    }

    private val amountTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (!updatingText)
                if (amountEditText1.hasFocus()) {
                    convertCurrency(fromEditText1 = true)
                } else if (amountEditText2.hasFocus()) {
                    convertCurrency(fromEditText1 = false)
                }
        }

        override fun afterTextChanged(s: Editable?) {}
    }

    private val currencyItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
            convertCurrency(fromEditText1 = amountEditText1.hasFocus())
        }

        override fun onNothingSelected(parent: AdapterView<*>) {}
    }

    private fun convertCurrency(fromEditText1: Boolean) {
        val sourceAmount = if (fromEditText1) amountEditText1.text.toString().toDoubleOrNull() else amountEditText2.text.toString().toDoubleOrNull()
        val sourceCurrency = if (fromEditText1) currencySpinner1.selectedItem.toString() else currencySpinner2.selectedItem.toString()
        val destinationCurrency = if (fromEditText1) currencySpinner2.selectedItem.toString() else currencySpinner1.selectedItem.toString()

        if (sourceAmount != null) {
            updatingText = true
            val result = convert(sourceAmount, sourceCurrency, destinationCurrency)
            val r = decimalFormat.format(result)
            if (fromEditText1) {
//                amountEditText2.setText(result.toString())
                amountEditText2.setText(r)
            } else {
                amountEditText1.setText(r)
            }
            updatingText = false
        }
    }

    private fun convert(amount: Double, fromCurrency: String, toCurrency: String): Double {
        // Placeholder conversion ratesval decimalFormat = DecimalFormat("#.##")
        val rates = mapOf(
            "USD" to 1.0,
            "EUR" to 0.923,
            "CNY" to 7.1215,
            "JPY" to 152.90,
            "VND" to 25280.0,
        )
        return (amount / rates[fromCurrency]!!) * rates[toCurrency]!!
    }
}
