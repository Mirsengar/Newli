package contagiouscode.mirsengar.newli.presentation.ui.screen.news

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NewsScreen(
          navController : NavController ,
          newsViewModel : NewsViewModel = hiltViewModel() ,
) {
     
     LaunchedEffect(key1 = true) {
          val category =
                    navController.previousBackStackEntry?.arguments?.getString("category")
                    ?: "technology"
          newsViewModel.getNewsByCategory(category)
          Log.d("SelectedCategory" , "NewsScreen: $category")
     }
     val news = newsViewModel.allNews.collectAsLazyPagingItems()
     Scaffold(
               topBar = {
                    NewsAppBar() {
                         navController.navigateUp()
                    }
               } ,
               backgroundColor = Color.Transparent ,
               content = {
                    ListContent(
                              news = news ,
                              navController = navController
                    )
               }
     )
}