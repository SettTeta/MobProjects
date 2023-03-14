package database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.studentlist.Student
import java.util.UUID

@Dao
interface StudentDAO {

    @Query("SELECT * FROM Student")
    fun getStudents(): LiveData<List<Student>>

    @Query("SELECT * FROM Student where id = (:id)")
    fun getStudent(id: UUID): LiveData<Student>

    @Insert
    fun insertStudent(s: Student)

    @Update
    fun updateStudent(s: Student)

    @Query("DELETE FROM Student")
    fun deleteAllStudents()

    @Query("DELETE FROM Student where id = (:id)")
    fun deleteStudent(id: UUID)
}