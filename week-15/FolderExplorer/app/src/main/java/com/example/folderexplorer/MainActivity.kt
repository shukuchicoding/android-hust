package com.example.folderexplorer

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.folderexplorer.FileContentActivity
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FileAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
        }

        val rootPath = Environment.getExternalStorageDirectory().absolutePath
        Log.v("TAG", rootPath)
        displayFiles(rootPath)
    }

    private fun displayFiles(path: String) {
        val directory = File(path)
        if (directory.exists() && directory.isDirectory) {
            val files = directory.listFiles()
            adapter = FileAdapter(files?.toList() ?: emptyList(), this) { file ->
                if (file.isDirectory) {
                    displayFiles(file.absolutePath)
                } else if (file.isFile && file.extension == "txt") {
                    openFile(file)
                } else {
                    Toast.makeText(this, "Không thể mở file này", Toast.LENGTH_SHORT).show()
                }
            }
            recyclerView.adapter = adapter
        }
    }

    private fun openFile(file: File) {
        val intent = Intent(this, FileContentActivity::class.java)
        intent.putExtra("filePath", file.absolutePath)
        startActivity(intent)
    }
}
