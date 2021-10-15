package ph.greggjover.appetiserexam.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ph.greggjover.appetiserexam.data.database.content.Content
import ph.greggjover.appetiserexam.data.database.content.ContentDao

/**
 * Room Database that stores all the data from the defined Entity classes
 */
@Database(entities = [Content::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contentDao(): ContentDao

    companion object {
        private const val databaseName = "appetiser.db"

        // Used for Singleton Instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            // Declaring synchronized allows the database to be
            // instantiated only once throughout the entire app
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    databaseName
                )
                    .fallbackToDestructiveMigration()
                    .build().also { instance = it }
            }
        }
    }

}