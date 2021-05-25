package pl.comarch.android.schronisko

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase

@Database(entities = [ZwierzDTO::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun zwierzakiDao(): ZwierzakiDao
}

@Dao
interface ZwierzakiDao {

    @Query("DELETE FROM zwierzdto")
    fun delete()

    @Query("SELECT * FROM zwierzdto ORDER BY zwierzdto.name DESC")
    fun get(): List<ZwierzDTO>

    @Insert
    fun insert(zwierzaki: List<ZwierzDTO>)

    @Insert
    fun insert(zwierz: ZwierzDTO)
}