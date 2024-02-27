package com.practicum.randomusercft.common.di

import android.app.Application
import androidx.room.Room
import com.practicum.randomusercft.common.db.UserDao
import com.practicum.randomusercft.common.db.UsersDatabase
import com.practicum.randomusercft.data.api.RandomUserApi
import com.practicum.randomusercft.data.impl.UsersRepositoryImpl
import com.practicum.randomusercft.domain.repository.UsersRepository
import com.practicum.randomusercft.domain.repository.UsersUseCases
import com.practicum.randomusercft.domain.usecase.DeleteUserUseCase
import com.practicum.randomusercft.domain.usecase.GetAllUsersUseCase
import com.practicum.randomusercft.domain.usecase.GetHistoryUsersUseCase
import com.practicum.randomusercft.domain.usecase.InsertUserUseCase
import com.practicum.randomusercft.utils.Constants.BASE_URL
import com.practicum.randomusercft.utils.Constants.USERS_DATABASE_NAME
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
/**
 * DI приложения
 */
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
        userDao: UserDao
    ): UsersRepository {
        return UsersRepositoryImpl(api, userDao)
    }

    @[Provides Singleton]
    fun provideGetAllUsersUseCases(
        repository: UsersRepository
    ): GetAllUsersUseCase {
        return GetAllUsersUseCase(
            usersRepository = repository
        )
    }

    @Provides
    @Singleton
    fun provideUserDatabase(
        application: Application
    ): UsersDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = UsersDatabase::class.java,
            name = USERS_DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(
        usersDatabase: UsersDatabase
    ): UserDao = usersDatabase.usersDao

    @Provides
    @Singleton
    fun provideUsersUseCases(
        usersRepository: UsersRepository
    ): UsersUseCases {
        return UsersUseCases(
            insertUserUseCase = InsertUserUseCase(usersRepository),
            deleteUserUseCase = DeleteUserUseCase(usersRepository),
            getHistoryUsersUseCase = GetHistoryUsersUseCase(usersRepository)
        )
    }
}