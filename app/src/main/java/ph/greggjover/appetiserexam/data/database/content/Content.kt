package ph.greggjover.appetiserexam.data.database.content

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Room Entity class that stores content in the Database.
 *
 * All columns listed are necessary in order to display the required content, both in list and
 * detail view.
 */
@Entity(tableName = "content")
@Parcelize
data class Content(
    @ColumnInfo val artworkUrlForDetail: String,
    @ColumnInfo val artworkUrlForList: String,
    @ColumnInfo val longDescription: String,
    @ColumnInfo val primaryGenreName: String,
    @ColumnInfo @PrimaryKey val trackName: String,
    @ColumnInfo val trackPrice: Double,
) : Parcelable