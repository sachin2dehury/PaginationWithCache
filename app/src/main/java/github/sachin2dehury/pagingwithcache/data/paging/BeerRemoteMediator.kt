package github.sachin2dehury.pagingwithcache.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import github.sachin2dehury.pagingwithcache.data.database.BeerDb
import github.sachin2dehury.pagingwithcache.data.database.BeerEntity
import github.sachin2dehury.pagingwithcache.data.repository.BeerRepository
import github.sachin2dehury.pagingwithcache.data.toBeerEntity

@OptIn(ExperimentalPagingApi::class)
class BeerRemoteMediator(
    private val beerRepository: BeerRepository,
    private val beerDb: BeerDb
) :
    RemoteMediator<Int, BeerEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BeerEntity>
    ): MediatorResult {
        return try {
            val offset = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem != null) {
                        ((lastItem.id ?: 0) / state.config.pageSize) + 1
                    } else {
                        1
                    }
                }
            }
            val limit = state.config.pageSize
            val beersList = beerRepository.getBeersRemote(offset, limit)

            val entityList = beersList?.map { it.toBeerEntity() }

            beerDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    beerRepository.deleteDb()
                }
                beerRepository.upsert(entityList)
            }

            val endOfPagination = beersList.isNullOrEmpty()
            MediatorResult.Success(endOfPaginationReached = endOfPagination)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}
