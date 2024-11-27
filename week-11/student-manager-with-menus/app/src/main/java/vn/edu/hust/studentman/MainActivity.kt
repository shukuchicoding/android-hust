package vn.edu.hust.studentman

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
  lateinit var optionLauncher: ActivityResultLauncher<Intent>
  lateinit var contextLauncher: ActivityResultLauncher<Intent>
  lateinit var studentAdapter: StudentAdapter
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
    setSupportActionBar(toolbar)

    val students = mutableListOf(
      StudentModel("Nguyễn Văn An", "SV001"),
      StudentModel("Trần Thị Bảo", "SV002"),
      StudentModel("Lê Hoàng Cường", "SV003"),
      StudentModel("Phạm Thị Dung", "SV004"),
      StudentModel("Đỗ Minh Đức", "SV005"),
      StudentModel("Vũ Thị Hoa", "SV006"),
      StudentModel("Hoàng Văn Hải", "SV007"),
      StudentModel("Bùi Thị Hạnh", "SV008"),
      StudentModel("Đinh Văn Hùng", "SV009"),
      StudentModel("Nguyễn Thị Linh", "SV010"),
      StudentModel("Phạm Văn Long", "SV011"),
      StudentModel("Trần Thị Mai", "SV012"),
      StudentModel("Lê Thị Ngọc", "SV013"),
      StudentModel("Vũ Văn Nam", "SV014"),
      StudentModel("Hoàng Thị Phương", "SV015"),
      StudentModel("Đỗ Văn Quân", "SV016"),
      StudentModel("Nguyễn Thị Thu", "SV017"),
      StudentModel("Trần Văn Tài", "SV018"),
      StudentModel("Phạm Thị Tuyết", "SV019"),
      StudentModel("Lê Văn Vũ", "SV020")
    )


    val parentView = findViewById<RecyclerView>(R.id.recycler_view_students)

    contextLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
      {
          it: ActivityResult ->
        if (it.resultCode == RESULT_OK) {
          val updatedName = it.data?.getStringExtra("updatedName")
          val updatedMssv = it.data?.getStringExtra("updatedMssv")
          val position = it.data?.getIntExtra("position", -1) ?: -1

          if (updatedName != null) {
            students[position].studentName = updatedName
          }
          if (updatedMssv != null) {
            students[position].studentId = updatedMssv
          }
          studentAdapter.notifyItemChanged(position)
        }
      }
    )

    studentAdapter = StudentAdapter(students, this@MainActivity, parentView, contextLauncher)

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
          if (hoten != null && mssv != null)
            students.add(StudentModel(studentName = hoten, studentId = mssv))
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
    studentAdapter.handleContextMenuAction(item)
    return super.onContextItemSelected(item)
  }
}