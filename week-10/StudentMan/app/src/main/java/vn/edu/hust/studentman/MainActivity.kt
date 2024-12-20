package vn.edu.hust.studentman

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

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
    val studentAdapter = StudentAdapter(students, this@MainActivity, parentView)

    parentView.run {
      adapter = studentAdapter
      layoutManager = LinearLayoutManager(this@MainActivity)
    }

//    val dialogView = LayoutInflater.from(this)
//      .inflate(R.layout.layout_student_info, null)
//
//    val editHoten = dialogView.findViewById<EditText>(R.id.editHoten)
//    val editMssv = dialogView.findViewById<EditText>(R.id.editMssv)

    findViewById<Button>(R.id.btn_add_new).setOnClickListener {
      val dialogView = LayoutInflater.from(this)
        .inflate(R.layout.layout_student_info, null)

      val editHoten = dialogView.findViewById<EditText>(R.id.editHoten)
      val editMssv = dialogView.findViewById<EditText>(R.id.editMssv)

      editHoten.text.clear()
      editMssv.text.clear()

      AlertDialog.Builder(this)
        .setTitle("Nhập thông tin sinh viên mới")
        .setView(dialogView)
        .setPositiveButton("Thêm",
          { _, _ ->
            val hoten = editHoten.text.toString()
            val mssv = editMssv.text.toString()
            if (hoten.isNotEmpty() && mssv.isNotEmpty()) {
              students.add(StudentModel(studentName = hoten, studentId = mssv))
              studentAdapter.notifyItemInserted(students.size - 1)
            } // Thông báo cho adapter biết một mục mới đã được thêm
          })
        .setNegativeButton("Hủy",
          { _, _ -> })
        .show()
    }
  }
}