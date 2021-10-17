package ph.greggjover.appetiserexam.data.network.response

import com.squareup.moshi.JsonClass

/**
 * Data response class that holds the list of [ContentResponse] items
 */
@JsonClass(generateAdapter = true)
data class GetContentResponse(
    val results: List<ContentResponse>,
)