package com.example.studentlist

import android.os.Bundle
import android.text.TextWatcher
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var studentAdapter: StudentAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private val studentList = mutableListOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        studentList.addAll(generateStudentList())

        studentAdapter = StudentAdapter(studentList)
        recyclerView.adapter = studentAdapter

        searchView = findViewById(R.id.searchView)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null && newText.length > 2) {
                    filterList(newText)
                } else {
                    studentAdapter.updateList(studentList)
                }
                return true
            }
        })
    }

    private fun filterList(query: String) {
        val filteredList = studentList.filter { student ->
            student.name.contains(query, ignoreCase = true) || student.id.contains(query)
        }
        studentAdapter.updateList(filteredList)
    }

    private fun generateStudentList(): List<Student> {
        return listOf(
            Student("20210001", "Nguyen Van A"),
            Student("20210002", "Le Thi B"),
            Student("20210003", "Tran Van C"),
            Student("20210004", "Pham Thi D"),
            Student("20210005", "Bui Van E"),
            Student("20210006", "Do Thi F"),
            Student("20210007", "Vo Van G"),
            Student("20210008", "Ho Thi H")
        )
    }
}
