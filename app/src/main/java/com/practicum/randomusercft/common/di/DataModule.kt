package com.practicum.randomusercft.common.di

import com.practicum.randomusercft.data.api.RandomUserApi
import com.practicum.randomusercft.data.impl.UsersRepositoryImpl
import com.practicum.randomusercft.domain.repository.UsersRepository
import com.practicum.randomusercft.domain.usecase.GetAllUsersUseCase
import com.practicum.randomusercft.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {


    @Singleton
    @Provides
    fun providesOkhttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): RandomUserApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(RandomUserApi::class.java)

    }

    @[Provides Singleton]
    fun provideUserRepository(
        api: RandomUserApi,

        ): UsersRepository {
        return UsersRepositoryImpl(api)
    }

    @[Provides Singleton]
    fun provideGetAllUsersUseCases(
        repository: UsersRepository
    ): GetAllUsersUseCase {
        return GetAllUsersUseCase(
            usersRepository = repository
        )
    }
}