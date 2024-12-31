package com.example.roomdatabase

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.roomdatabase.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.main)
        val studentDao = StudentDatabase.getInstance(this).studentDao()

        binding.buttonInsert.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val result = studentDao.insertStudent(Student(
                    hoten = binding.editHoten.text.toString(),
                    mssv = binding.editMssv.text.toString()
                ))
                Log.v("TAG", "Result: $result")
            }
        }

        binding.buttonGetAll.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val students = studentDao.getAllStudents()
                for (student in students)
                    Log.v("TAG", "$student")
            }
        }

        binding.buttonSearchName.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val name = binding.editHoten.text.toString()
                val students = studentDao.getStudentsByName(name)
                for (student in students)
                    Log.v("TAG", "$student")
            }
        }

        binding.buttonSearchMs.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val mssv = binding.editMssv.text.toString()
                val students = studentDao.getStudentByMssv(mssv)
                for (student in students)
                    Log.v("TAG", "$student")
            }
        }

        binding.buttonUpdate.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val student = Student(
                    _id = binding.editId.text.toString().toInt(),
                    hoten = binding.editHoten.text.toString(),
                    mssv = binding.editMssv.text.toString()
                )

                val result = studentDao.updateStudent(student)
                Log.v("TAG", "Result $result")
            }
        }

        binding.buttonDelete.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
//        val student = Student(
//          _id = binding.editId.text.toString().toInt(),
//          hoten = binding.editHoten.text.toString(),
//          mssv = binding.editMssv.text.toString()
//        )
//
//        val result = studentDao.deleteStudent(student)
//        Log.v("TAG", "Result $result")

                val result = studentDao.deleteByMssv(binding.editMssv.text.toString())
                Log.v("TAG", "Result $result")
            }
        }
    }
}