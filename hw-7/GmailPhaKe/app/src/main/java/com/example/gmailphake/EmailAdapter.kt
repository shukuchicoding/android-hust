package com.example.gmailphake

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Email(
    val sender: String,
    val preview: String,
    val time: String)
class EmailAdapter(private val emailList: List<Email>) : RecyclerView.Adapter<EmailAdapter.EmailViewHolder>() {
    class EmailViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewSender: TextView = view.findViewById(R.id.textViewSender)
        val textViewPreview: TextView = view.findViewById(R.id.textViewPreview)
        val textViewTime: TextView = view.findViewById(R.id.textViewTime)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_email, parent, false)
        return EmailViewHolder(view)
    }
    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        val email = emailList[position]
        holder.textViewSender.text = email.sender
        holder.textViewPreview.text = email.preview
        holder.textViewTime.text = email.time
    }
    override fun getItemCount() = emailList.size
}