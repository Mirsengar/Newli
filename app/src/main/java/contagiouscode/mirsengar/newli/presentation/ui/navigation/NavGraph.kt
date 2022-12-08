package contagiouscode.mirsengar.newli.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import contagiouscode.mirsengar.newli.data.model.NewsResponse
import contagiouscode.mirsengar.newli.presentation.ui.screen.details.DetailScreen
import contagiouscode.mirsengar.newli.presentation.ui.screen.home.HomeScreen
import contagiouscode.mirsengar.newli.presentation.ui.screen.intro.SplashScreen
import contagiouscode.mirsengar.newli.presentation.ui.screen.news.NewsScreen
import contagiouscode.mirsengar.newli.utils.parcelable

@Composable
fun AppNavGraph(
          navController : NavHostController ,
) {
     
     NavHost(
               navController = navController ,
               startDestination = Screen.Splash.route
     ) {
          
          composable(route = Screen.Splash.route) {
               SplashScreen(navController = navController)
          }
          composable(route = Screen.Home.route) {
               HomeScreen(navController = navController)
          }
          composable(
                    route = Screen.Detail.route
          ) {
               val newsItem =
                         navController.previousBackStackEntry?.arguments?.parcelable<NewsResponse.Article>(
                                   "news"
                         )
               DetailScreen(navController = navController , newsItem = newsItem)
          }
          composable(route = Screen.News.route) {
               NewsScreen(
                         navController = navController
               )
          }
     }
}