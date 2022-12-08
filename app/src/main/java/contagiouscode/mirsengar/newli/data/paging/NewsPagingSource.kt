package contagiouscode.mirsengar.newli.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import contagiouscode.mirsengar.newli.data.model.NewsResponse
import contagiouscode.mirsengar.newli.data.remote.NewsApi
import contagiouscode.mirsengar.newli.utils.NETWORK_PAGE_SIZE
import contagiouscode.mirsengar.newli.utils.STARTING_INDEX
import retrofit2.HttpException
import java.io.IOException

class NewsPagingSource(
          private val newsApi : NewsApi ,
          private val category : String ,
) : PagingSource<Int , NewsResponse.Article>() {
     override fun getRefreshKey(state : PagingState<Int , NewsResponse.Article>) : Int? {
          return state.anchorPosition
     }
     
     override suspend fun load(params : LoadParams<Int>) : LoadResult<Int , NewsResponse.Article> {
          val position = params.key ?: STARTING_INDEX
          return try {
               val data = newsApi.getNewsByCategory(
                         category = category ,
                         page = position ,
                         pageSize = params.loadSize
               )
               val nextKey = if (data.articles.isEmpty())
                    null
               else {
                    position + (params.loadSize / NETWORK_PAGE_SIZE)
               }
               val prevKey = if (position == STARTING_INDEX) null else position - 1
               LoadResult.Page(
                         data = data.articles ,
                         prevKey = prevKey ,
                         nextKey = nextKey
               )
          }
          catch (e : IOException) {
               return LoadResult.Error(e)
          }
          catch (e : HttpException) {
               return LoadResult.Error(e)
          }
     }
}
