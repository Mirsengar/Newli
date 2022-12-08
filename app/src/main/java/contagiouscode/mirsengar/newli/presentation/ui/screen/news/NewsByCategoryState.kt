package contagiouscode.mirsengar.newli.presentation.ui.screen.news

import androidx.paging.PagingData
import contagiouscode.mirsengar.newli.data.model.NewsResponse

data class NewsByCategoryState(
          var isLoading : Boolean = false ,
          val news : PagingData<NewsResponse.Article> ,
          val error : String? = "" ,
)

