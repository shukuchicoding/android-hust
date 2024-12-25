package com.example.folderexplorer

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.folderexplorer.R
import java.io.File

class FileContentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_content)

        val textView: TextView = findViewById(R.id.textViewContent)
        val filePath = intent.getStringExtra("filePath")

        filePath?.let {
            val file = File(it)
            if (file.exists() && file.isFile) {
                val content = file.readText()
                textView.text = content
            } else {
                textView.text = "Không thể đọc file."
            }
        }
    }
}
