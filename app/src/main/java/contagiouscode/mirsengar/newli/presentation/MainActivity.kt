package contagiouscode.mirsengar.newli.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.rememberNavController
import contagiouscode.mirsengar.newli.R
import contagiouscode.mirsengar.newli.presentation.ui.navigation.AppNavGraph
import contagiouscode.mirsengar.newli.presentation.ui.theme.NewliThme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
     override fun onCreate(savedInstanceState : Bundle?) {
          super.onCreate(savedInstanceState)
          setContent {
               NewliThme {
                    val navController = rememberNavController()
                    Box(modifier = Modifier) {
                         Image(
                                   modifier = Modifier.fillMaxSize() ,
                                   painter = painterResource(id = R.drawable.background) ,
                                   contentScale = ContentScale.Crop ,
                                   contentDescription = null)
                         AppNavGraph(navController = navController)
                    }
               }
          }
     }
}