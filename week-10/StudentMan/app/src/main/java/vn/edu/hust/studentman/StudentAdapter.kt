package vn.edu.hust.studentman

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(val students: MutableList<StudentModel>, val context: Context): RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
  class StudentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val textStudentName: TextView = itemView.findViewById(R.id.text_student_name)
    val textStudentId: TextView = itemView.findViewById(R.id.text_student_id)
    val imageEdit: ImageView = itemView.findViewById(R.id.image_edit)
    val imageRemove: ImageView = itemView.findViewById(R.id.image_remove)
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

    holder.imageRemove.setOnClickListener {
      removeStudent(position)
    }

    holder.imageEdit.setOnClickListener {
      showEditDialog(position)
    }
  }

  fun removeStudent(position: Int) {
    students.removeAt(position)
    notifyItemRemoved(position)
    notifyItemChanged(position, students.size)
  }

  fun showEditDialog(position: Int) {
    val student = students[position]

    val view = LayoutInflater.from(context).inflate(R.layout.layout_student_info, null)

    val editHoten = view.findViewById<EditText>(R.id.editHoten)
    editHoten.setText(student.studentName)
    val editMssv = view.findViewById<EditText>(R.id.editMssv)
    editMssv.setText(student.studentId)

    AlertDialog.Builder(context)
      .setTitle("Thông tin sinh viên")
      .setView(view)
      .setPositiveButton("Lưu",
        { _, _ ->
          student.studentName = editHoten.text.toString()
          student.studentId = editMssv.text.toString()
          notifyItemChanged(position)
        })
      .setNegativeButton("Hủy",
        { _, _ -> })
      .setNeutralButton("Thoát",
        { _, _ -> })
      .show()
  }
}