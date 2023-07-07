package github.sachin2dehury.pagingwithcache.data.repository

import github.sachin2dehury.pagingwithcache.data.database.BeerDao
import github.sachin2dehury.pagingwithcache.data.database.BeerEntity

class BeerRepository(
    private val beerApiService: BeerApiService,
    private val beerDao: BeerDao
) {

    suspend fun getBeersRemote(offset: Int, limit: Int) = beerApiService.getBeers(offset, limit)

    fun getBeersLocal() = beerDao.getBeers()

    suspend fun deleteDb() = beerDao.deleteAll()

    suspend fun upsert(items: List<BeerEntity>?) = beerDao.upsert(items)
}