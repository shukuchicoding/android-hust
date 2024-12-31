package vn.edu.hust.studentman

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        val editHoten = findViewById<EditText>(R.id.editHoten)
        val editMssv = findViewById<EditText>(R.id.editMssv)

        findViewById<Button>(R.id.button_ok).setOnClickListener {
            val hoten = editHoten.text.toString()
            val mssv = editMssv.text.toString()
            if (hoten.isEmpty()) {
                editHoten.error = "Họ tên không được để trống"
                return@setOnClickListener
            }

            if (mssv.isEmpty()) {
                editMssv.error = "MSSV không được để trống"
                return@setOnClickListener
            }
            intent.putExtra("hoten", hoten)
            intent.putExtra("mssv", mssv)

            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        findViewById<Button>(R.id.button_cancle).setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}