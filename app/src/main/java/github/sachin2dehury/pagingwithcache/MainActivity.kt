package github.sachin2dehury.pagingwithcache

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import dagger.hilt.android.AndroidEntryPoint
import github.sachin2dehury.pagingwithcache.ui.BeerScreen
import github.sachin2dehury.pagingwithcache.ui.BeerViewModel
import github.sachin2dehury.pagingwithcache.ui.theme.PagingWithCacheTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PagingWithCacheTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = hiltViewModel<BeerViewModel>()
                    val beerList = viewModel.beerPagingFlow.collectAsLazyPagingItems()
                    BeerScreen(beerList = beerList)
                }
            }
        }
    }
}
