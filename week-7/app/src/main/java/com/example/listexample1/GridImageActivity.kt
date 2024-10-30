package com.example.listexample1

import android.os.Bundle
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.TypedValueCompat

class GridImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid_image)

        val thumbs = mutableListOf<Int>()
        repeat(6) {
            thumbs.add(resources.getIdentifier("thumb${it + 1}", "drawable", packageName))
        }

        val space = TypedValueCompat.dpToPx(8f, resources.displayMetrics)
        val imageSize: Int = ((resources.displayMetrics.widthPixels - space) / 3).toInt()

        val adapter = ImageAdapter(thumbs, imageSize)

        val gridImages = findViewById<GridView>(R.id.grid_view)
        gridImages.adapter = adapter
    }
}