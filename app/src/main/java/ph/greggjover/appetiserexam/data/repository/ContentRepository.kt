package ph.greggjover.appetiserexam.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ph.greggjover.appetiserexam.data.database.AppDatabase
import ph.greggjover.appetiserexam.data.database.content.Content
import ph.greggjover.appetiserexam.data.database.content.GenreWithContent
import ph.greggjover.appetiserexam.data.network.RetrofitService
import javax.inject.Inject

/**
 * Repository class that contains method calls to be used by ViewModels
 */
class ContentRepository @Inject constructor(
    private val db: AppDatabase,
    private val service: RetrofitService,
) {

    /**
     * This function retrieves content from [AppDatabase]. [Flow] is used to observe and
     * immediately any Content changes in [AppDatabase] and groups the data
     * into a list of [GenreWithContent] to be displayed in the UI
     *
     * @return - a [Flow] of a list of [GenreWithContent]
     */
    fun observeContent(): Flow<List<GenreWithContent>> =
        db.contentDao().getAll().map { contents ->
            contents.map { it.primaryGenreName }
                .distinct()
                .map { genre ->
                    GenreWithContent(genre, contents.filter { it.primaryGenreName == genre })
                }
        }

    /**
     * Retrieves content from the backend using [RetrofitService] and stores the parsed response
     * to [AppDatabase]
     */
    suspend fun getContent() {
        val response = service.searchContent(
            term = "star",
            country = "all",
            media = "movie",
        )

        db.contentDao().insert(response.results.map {
            Content(
                artworkUrlForDetail = it.artworkUrl100,
                artworkUrlForList = it.artworkUrl60,
                longDescription = it.longDescription,
                primaryGenreName = it.primaryGenreName,
                trackName = it.trackName,
                trackPrice = it.trackPrice
            )
        })
    }

}