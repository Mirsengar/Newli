package contagiouscode.mirsengar.newli.presentation.ui.screen.news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import contagiouscode.mirsengar.newli.data.model.NewsResponse
import contagiouscode.mirsengar.newli.presentation.ui.components.ShimmerEffect
import contagiouscode.mirsengar.newli.presentation.ui.navigation.Screen
import contagiouscode.mirsengar.newli.presentation.ui.theme.Black
import contagiouscode.mirsengar.newli.presentation.ui.theme.SMALL_PADDING

@Composable
fun ListContent(
          news : LazyPagingItems<NewsResponse.Article> ,
          navController : NavController ,
) {
     val result = handlePagingResult(news = news)
     if (result) {
          LazyColumn(
                    contentPadding = PaddingValues(horizontal = 8.dp) ,
                    verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
          ) {
               items(
                         items = news ,
                         key = { news ->
                              news.url
                         }
               ) { news ->
                    news?.let {
                         NewsItem(news = it) {
                              navController.currentBackStackEntry?.arguments?.putParcelable("news" , news)
                              navController.navigate(Screen.Detail.route)
                         }
                         Box(
                                   modifier = Modifier
                                             .fillMaxWidth()
                                             .padding(horizontal = 8.dp)
                                             .height(1.dp)
                                             .background(Black)
                         )
                    }
               }
          }
     }
}
@Composable
fun handlePagingResult(
          news : LazyPagingItems<NewsResponse.Article> ,
) : Boolean {
     news.apply {
          val error = when {
               loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
               loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
               loadState.append is LoadState.Error  -> loadState.append as LoadState.Error
               else                                 -> null
          }
          return when {
               loadState.refresh is LoadState.Loading -> {
                    ShimmerEffect()
                    false
               }
               error != null                          -> {
                    EmptyScreen(error = error)
                    false
               }
               news.itemCount < 1                     -> {
                    false
               }
               else                                   -> true
          }
     }
}


@Composable
fun EmptyScreen(error : LoadState.Error? = null) {
     Column(modifier = Modifier.fillMaxSize()) {
          Text(text = "EMPTY")
     }
}