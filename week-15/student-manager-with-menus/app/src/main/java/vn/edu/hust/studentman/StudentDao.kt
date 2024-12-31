package vn.edu.hust.studentman

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface StudentDao {
    @Query("select * from students")
    suspend fun getAllStudents(): Array<Student>

    @Query("select * from students where studentId = :ms")
    suspend fun getStudentByMssv(ms: String): Array<Student>

    @Query("select * from students where studentName like '%' || :name || '%'")
    suspend fun getStudentsByName(name: String): Array<Student>

    @Insert
    suspend fun insertStudent(student: Student): Long

    @Update
    suspend fun updateStudent(student: Student): Int

    @Query("update students set studentId = :newms, studentName = :newName where studentId = :oldms")
    suspend fun updateByMssv(oldms: String, newms: String, newName: String): Int

    @Delete
    suspend fun deleteStudent(student: Student): Int

    @Query("delete from students where studentId = :ms")
    suspend fun deleteByMssv(ms: String): Int
}