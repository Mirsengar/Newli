package contagiouscode.mirsengar.newli.di

import contagiouscode.mirsengar.newli.data.remote.NewsApi
import contagiouscode.mirsengar.newli.data.repository.NewsRepositoryImpl
import contagiouscode.mirsengar.newli.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
     @Provides
     @Singleton
     fun provideRepository(newsApi : NewsApi) : NewsRepository {
          return NewsRepositoryImpl(newsApi)
     }
}