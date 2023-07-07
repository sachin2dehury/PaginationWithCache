package github.sachin2dehury.pagingwithcache.ui

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import github.sachin2dehury.pagingwithcache.data.database.BeerDb
import github.sachin2dehury.pagingwithcache.data.paging.BeerRemoteMediator
import github.sachin2dehury.pagingwithcache.data.repository.BeerRepository
import github.sachin2dehury.pagingwithcache.data.toBeer
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class BeerViewModel @Inject constructor(
    private val repository: BeerRepository,
    private val beerDb: BeerDb
) : ViewModel() {

    val beerPagingFlow = getPager().flow
        .catch { it.printStackTrace() }
        .map { it.map { it.toBeer() } }

    @OptIn(ExperimentalPagingApi::class)
    private fun getPager() = Pager(
        PagingConfig(10),
        1,
        BeerRemoteMediator(repository, beerDb)
    ) { repository.getBeersLocal() }
}