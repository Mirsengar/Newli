package contagiouscode.mirsengar.newli.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import contagiouscode.mirsengar.newli.data.model.NewsResponse
import contagiouscode.mirsengar.newli.data.paging.NewsPagingSource
import contagiouscode.mirsengar.newli.data.paging.SearchNewsPagingSource
import contagiouscode.mirsengar.newli.data.remote.NewsApi
import contagiouscode.mirsengar.newli.domain.repository.NewsRepository
import contagiouscode.mirsengar.newli.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
          private val newsApi : NewsApi ,
) : NewsRepository {
     
     override fun getTopHeadlines() : Flow<Resource<NewsResponse>> = flow {
          try {
               emit(Resource.Loading())
               val result = newsApi.getTopHeadLines()
               emit(Resource.Success(result))
          }
          catch (e : IOException) {
               e.printStackTrace()
               emit(
                         Resource.Error(
                                   message = "Couldn't load Headlines"
                         )
               )
          }
          catch (e : HttpException) {
               e.printStackTrace()
               emit(
                         Resource.Error(
                                   message = "Couldn't load Headlines"
                         )
               )
          }
     }
     
     override fun getEverything() : Flow<Resource<NewsResponse>> = flow {
          try {
               emit(Resource.Loading())
               val result = newsApi.getEverything()
               emit(Resource.Success(result))
          }
          catch (e : IOException) {
               e.printStackTrace()
               emit(
                         Resource.Error(
                                   message = "Couldn't load News"
                         )
               )
          }
          catch (e : HttpException) {
               e.printStackTrace()
               emit(
                         Resource.Error(
                                   message = "Couldn't load News"
                         )
               )
          }
     }
     
     override fun getNewsByCategory(
               category : String ,
     ) : Flow<PagingData<NewsResponse.Article>> = Pager(
               config = PagingConfig(
                         pageSize = 10 ,
               ) ,
               pagingSourceFactory = {
                    NewsPagingSource(newsApi , category)
               }
     ).flow
     
     override fun searchNews(query : String) : Flow<PagingData<NewsResponse.Article>> = Pager(
               config = PagingConfig(
                         pageSize = 10 ,
               ) ,
               pagingSourceFactory = {
                    SearchNewsPagingSource(newsApi , query)
               }
     ).flow
     
     override fun searchNewsFlow(query : String) : Flow<PagingData<NewsResponse.Article>> = Pager(
               config = PagingConfig(
                         pageSize = 10 ,
               ) ,
               pagingSourceFactory = {
                    SearchNewsPagingSource(newsApi , query)
               }
     ).flow
     
     override fun getNewsCategories() = listOf(
               "Sports" , "Health" , "Entertainment" , "Science" , "Business" , "Technology"
     )
}