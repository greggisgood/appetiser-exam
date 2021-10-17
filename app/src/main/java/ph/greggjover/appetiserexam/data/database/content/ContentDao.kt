package ph.greggjover.appetiserexam.data.database.content

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Room Data Access Object class that performs SQL Commands for [Content]
 */
@Dao
interface ContentDao {

    /**
     * Retrieves all available [Content] from the table
     */
    @Query("SELECT * FROM content")
    fun getAll(): Flow<List<Content>>

    /**
     * Inserts [Content] into the table
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(content: List<Content>)

    /**
     * Deletes all [Content] from the table
     */
    @Query("DELETE FROM content")
    fun deleteAll()
}