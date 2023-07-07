package github.sachin2dehury.pagingwithcache.data.repository

import retrofit2.http.GET
import retrofit2.http.Query

interface BeerApiService {

    @GET("beers")
    suspend fun getBeers(
        @Query("page") offset: Int,
        @Query("per_page") limit: Int
    ): List<BeerResponse>?

    companion object {
        const val BASE_URL = "https://api.punkapi.com/v2/"
    }
}
