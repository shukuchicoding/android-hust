package vn.edu.hust.studentman

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
  lateinit var optionLauncher: ActivityResultLauncher<Intent>
  lateinit var contextLauncher: ActivityResultLauncher<Intent>
  lateinit var studentAdapter: StudentAdapter
  lateinit var students: MutableList<StudentModel>
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
    setSupportActionBar(toolbar)

    val studentDao = StudentDatabase.getInstance(this).studentDao()
    students = mutableListOf()

    lifecycleScope.launch(Dispatchers.IO) {
      val studentsList = studentDao.getAllStudents()
      for (student in studentsList)
        students.add(StudentModel(studentName = student.studentName, studentId = student.studentId))
    }

    val parentView = findViewById<RecyclerView>(R.id.recycler_view_students)

    // update std
    contextLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
      {
          it: ActivityResult ->
        if (it.resultCode == RESULT_OK) {
          val updatedName = it.data?.getStringExtra("updatedName")
          val updatedMssv = it.data?.getStringExtra("updatedMssv")
          val position = it.data?.getIntExtra("position", -1) ?: -1

          val oldName = students[position].studentName
          val oldSid = students[position].studentId

          if (updatedName != null && updatedMssv != null) {
            students[position].studentName = updatedName
            students[position].studentId = updatedMssv
            lifecycleScope.launch(Dispatchers.IO) {
              studentDao.updateByMssv(oldSid, updatedMssv, updatedName)
            }
            studentAdapter.notifyItemChanged(position)

          }
        }
      }
    )

    studentAdapter = StudentAdapter(students, this@MainActivity, parentView, contextLauncher, studentDao)

    parentView.run {
      adapter = studentAdapter
      layoutManager = LinearLayoutManager(this@MainActivity)
    }

    optionLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
      {
        it: ActivityResult ->
        if (it.resultCode == RESULT_OK) {
          val hoten = it.data?.getStringExtra("hoten")
          val mssv = it.data?.getStringExtra("mssv")
          if (hoten != null && mssv != null) {
            students.add(StudentModel(studentName = hoten, studentId = mssv))
            lifecycleScope.launch(Dispatchers.IO) {
              studentDao.insertStudent(Student(
                studentId = mssv,
                studentName = hoten
              ))
            }
          }
          studentAdapter.notifyItemInserted(students.size - 1)
        }
      }
    )


  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.action_add_student -> {
        val intent = Intent(this, AddStudentActivity::class.java)
        optionLauncher.launch(intent)
      }
    }
    return super.onOptionsItemSelected(item)
  }

  override fun onContextItemSelected(item: MenuItem): Boolean {
    lifecycleScope.launch {
      studentAdapter.handleContextMenuAction(item)
    }
    return super.onContextItemSelected(item)
  }

}