package github.sachin2dehury.pagingwithcache.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import github.sachin2dehury.pagingwithcache.data.database.BeerDao
import github.sachin2dehury.pagingwithcache.data.database.BeerDb
import github.sachin2dehury.pagingwithcache.data.repository.BeerApiService
import github.sachin2dehury.pagingwithcache.data.repository.BeerApiService.Companion.BASE_URL
import github.sachin2dehury.pagingwithcache.data.repository.BeerRepository
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideBeerDb(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, BeerDb::class.java, "beer_db").build()

    @Provides
    @Singleton
    fun provideBeerDao(db: BeerDb) = db.dao

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideBeerServiceApi(retrofit: Retrofit) = retrofit.create<BeerApiService>()

    @Provides
    @Singleton
    fun provideBeerRepository(service: BeerApiService, dao: BeerDao) = BeerRepository(service, dao)

}