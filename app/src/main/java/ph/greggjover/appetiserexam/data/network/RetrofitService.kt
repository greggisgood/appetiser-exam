package ph.greggjover.appetiserexam.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import ph.greggjover.appetiserexam.data.network.response.GetContentResponse
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit Service Class that will call endpoints and parse responses
 */
interface RetrofitService {

    /**
     * Retrieves the content from the iTunes store. The type of content returned will depend on the
     * parameters supplied.
     *
     * @param term - Text to be searched (e.g. star)
     * @param country - Two-letter country of the store (e.g. US)
     * @param media - Media type to search (e.g. movie)
     */
    @GET("search")
    suspend fun searchContent(
        @Query("term") term: String,
        @Query("country") country: String,
        @Query("media") media: String,
    ) : GetContentResponse

    companion object {
        private const val BASE_URL = "https://itunes.apple.com/"

        fun create(): RetrofitService {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(RetrofitService::class.java)
        }
    }
}