package com.example.studentlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(private var students: List<Student>) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    fun updateList(newList: List<Student>) {
        students = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_2, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.bind(student)
    }

    override fun getItemCount(): Int {
        return students.size
    }

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(android.R.id.text1)
        private val idTextView: TextView = itemView.findViewById(android.R.id.text2)

        fun bind(student: Student) {
            nameTextView.text = student.name
            idTextView.text = "ID: ${student.id}"
        }
    }
}
