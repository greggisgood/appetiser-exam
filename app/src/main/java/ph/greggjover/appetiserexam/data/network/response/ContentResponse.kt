package ph.greggjover.appetiserexam.data.network.response

import com.squareup.moshi.JsonClass

/**
 * Data response class that contains the required information to be displayed either in
 * List or Detail View
 */
@JsonClass(generateAdapter = true)
data class ContentResponse(
    val artworkUrl100: String,
    val longDescription: String,
    val primaryGenreName: String,
    val trackName: String,
    val trackPrice: Double,
)
