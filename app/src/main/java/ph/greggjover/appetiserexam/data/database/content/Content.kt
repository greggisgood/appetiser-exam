package ph.greggjover.appetiserexam.data.database.content

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room Entity class that stores content in the Database.
 *
 * All columns listed are necessary in order to display the required content, both in list and
 * detail view.
 */
@Entity(tableName = "content")
data class Content(
    @ColumnInfo val artworkUrl: String,
    @ColumnInfo val longDescription: String,
    @ColumnInfo val primaryGenreName: String,
    @ColumnInfo @PrimaryKey val trackName: String,
    @ColumnInfo val trackPrice: Double,
)