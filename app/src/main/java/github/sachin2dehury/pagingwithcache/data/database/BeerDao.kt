package github.sachin2dehury.pagingwithcache.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface BeerDao {

    @Upsert
    suspend fun upsert(items: List<BeerEntity>?)

    @Query("select * from beerentity")
    fun getBeers(): PagingSource<Int, BeerEntity>

    @Query("delete from beerentity")
    suspend fun deleteAll()
}
