package vn.edu.hust.studentman

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class StudentAdapter(val students: MutableList<StudentModel>, val context: Context, val parentView: View, val launcher: ActivityResultLauncher<Intent>): RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

  class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    View.OnCreateContextMenuListener {

    val textStudentName: TextView = itemView.findViewById(R.id.text_student_name)
    val textStudentId: TextView = itemView.findViewById(R.id.text_student_id)

    init {
      // Đăng ký ContextMenu cho mỗi View
      itemView.setOnCreateContextMenuListener(this)
    }

    override fun onCreateContextMenu(
      menu: ContextMenu?,
      v: View?,
      menuInfo: ContextMenu.ContextMenuInfo?
    ) {
      menu?.setHeaderTitle("Select Action")
      menu?.add(adapterPosition, 0, 0, "Edit")
      menu?.add(adapterPosition, 1, 1, "Remove")
    }
  }


  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
    val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_student_item,
       parent, false)
    return StudentViewHolder(itemView)
  }

  override fun getItemCount(): Int = students.size

  override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
    val student = students[position]

    holder.textStudentName.text = student.studentName
    holder.textStudentId.text = student.studentId
  }

  fun editStudent(position: Int) {
    val student = students[position]
    val view = LayoutInflater.from(context).inflate(R.layout.activity_edit_student, null)

    val editHoten = view.findViewById<EditText>(R.id.edit2Hoten)
    editHoten.setText(student.studentName)
    val editMssv = view.findViewById<EditText>(R.id.edit2Mssv)
    editMssv.setText(student.studentId)

    val intent = Intent(context, EditStudentActivity::class.java)
    intent.putExtra("hoten", editHoten.text.toString())
    intent.putExtra("mssv", editMssv.text.toString())
    intent.putExtra("position", position)
    launcher.launch(intent)
  }

  fun removeStudent(position: Int) {
    val student = students[position]

    students.removeAt(position)
    Log.v("TAG", "DELETE")
    Snackbar.make(parentView, "Đã xóa ${position}: ${student.studentName} - ${student.studentId}", Snackbar.LENGTH_LONG)
      .setAction("Hoàn tác") {
        students.add(position, student)
        notifyDataSetChanged()
      }.show()

    notifyDataSetChanged()
  }

  fun handleContextMenuAction(item: MenuItem) {
    val position = item.groupId
    when (item.itemId) {
      0 -> editStudent(position) // Edit
      1 -> removeStudent(position) // Remove
    }
  }
}