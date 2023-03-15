package database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.studentlist.Gadget
import java.util.UUID

@Dao
interface GadgetDAO {

    @Query("SELECT * FROM Gadget")
    fun getGadgets(): LiveData<List<Gadget>>

    @Query("SELECT * FROM Gadget WHERE id = (:id)")
    fun getGadget(id: UUID): LiveData<Gadget>

    @Insert
    fun insertGadget(s: Gadget)

    @Update
    fun updateGadget(s: Gadget)

    @Query("DELETE FROM Gadget WHERE id = (:id)")
    fun deleteGadget(id: UUID)

    @Query("DELETE FROM Gadget")
    fun deleteGadgets()

}