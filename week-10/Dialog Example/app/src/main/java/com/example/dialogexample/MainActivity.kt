package com.example.dialogexample

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.button1).setOnClickListener {
            AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("Dialog 1")
                .setMessage("This is content of dialog")
                .setPositiveButton("YES",
                    { _, _ -> Log.v("TAG", "YES is clicked") })
                .setNegativeButton("NO",
                    { _, _ -> Log.v("TAG", "NO is clicked") })
                .setNeutralButton("CANCEL", null)
                .show()
                .setCanceledOnTouchOutside(false)
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Dialog 2")
                .setItems(R.array.test_array,
                    { _, index -> Log.v("TAG", "$index is touched")})
                .setPositiveButton("Cancel", null)
                .show()
        }

//        val items = MutableList(10) { i -> "option ${i + 1}" }

        findViewById<Button>(R.id.button3).setOnClickListener {
//            AlertDialog.Builder(this)
//                .setTitle("Dialog 3")
//                .setItems(items.toTypedArray(),
//                    { _, index -> Log.v("TAG", "$index is touched")})
//                .setPositiveButton("Cancel", null)
//                .show()
//            AlertDialog.Builder(this)
//                .setTitle("Dialog 3")
//                .setItems(items.toTypedArray(),
//                    { _, index -> Log.v("TAG", "$index is touched")})
//                .setSingleChoiceItems(items.toTypedArray(), 0, {_, index -> Log.v("TAG", "$index is touched")})
//                .setPositiveButton("OK", {_, _ -> })
//                .setNegativeButton("CANCEL", null)
//                .show()

//            val selections = BooleanArray(items.size)
//            AlertDialog.Builder(this)
//                .setTitle("Dialog 3")
//                .setMultiChoiceItems(items.toTypedArray(), selections, { _, index, isSelected -> Log.v("TAG", "$index: $isSelected")})
//                .setPositiveButton("OK", {_, _ -> })
//                .setNegativeButton("CANCEL", null)
//                .show()

            val dialogView = LayoutInflater.from(this)
                .inflate(R.layout.layout_alert_dialog, null)
            val editHoten = dialogView.findViewById<EditText>(R.id.editText1)
            val editMssv = dialogView.findViewById<EditText>(R.id.editText2)

            AlertDialog.Builder(this)
                .setTitle("Nhập thông tin sinh viên")
                .setView(dialogView)
                .setPositiveButton("OK",
                    {_, _ ->
                        val hoten = editHoten.text.toString()
                        val mssv = editMssv.text.toString()
                        Log.v("TAG", "$hoten - $mssv")
                        Toast.makeText(this, "Xin chào $hoten - $mssv!", Toast.LENGTH_LONG).show()
                        Snackbar.make(it, "Bạn vừa nhập thông tin!", Snackbar.LENGTH_LONG)
                            .setAction("UNDO", {})
                            .show()
                    })
                .setNegativeButton("CANCEL", null)
                .show()
//            val dialog = Dialog(this)
//            dialog.setContentView(R.layout.layout_dialog)
//            val editHoten = dialog.findViewById<EditText>(R.id.editText1)
//            val editMssv = dialog.findViewById<EditText>(R.id.editText2)
//            dialog.findViewById<Button>(R.id.button_ok).setOnClickListener {
//                val hoten = editHoten.text.toString()
//                val mssv = editMssv.text.toString()
//            }
//            dialog.findViewById<Button>(R.id.button_cancel).setOnClickListener {
//
//            }
        }
    }
}