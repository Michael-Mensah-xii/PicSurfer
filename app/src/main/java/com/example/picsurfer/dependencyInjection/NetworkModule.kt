package com.example.picsurfer.dependencyInjection

import com.example.picsurfer.data.remote.UnsplashApi
import com.example.picsurfer.util.Constants.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/** a network module is typically used to define and configure the network components of the app, such as API clients,
 * interceptors, and other dependencies related to networking.
 * The network module is usually defined as a separate module within the app to make it easier to manage and test.
 * In this case, the @Module annotation is used to define a Dagger Hilt module for network components in a Kotlin app.
 * The @InstallIn annotation is used to specify the component in which this module should be installed. In this case,
 * it's installed in the SingletonComponent, which is a predefined component
 * that's used to manage singleton instances of dependencies throughout the app.*/


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }


    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        val json = Json {
            ignoreUnknownKeys = true // because when we send a get request the response in the json format has many properties we don't need
        }

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }


    @Provides
    @Singleton
    fun provideUnsplashApi(retrofit: Retrofit): UnsplashApi {
        return retrofit.create(UnsplashApi::class.java)
    }

}


/**The purpose of this module is to provide dependencies for the network layer of the app.
 * It includes three methods that use the @Provides annotation to create and configure
 * instances of OkHttpClient, Retrofit, and UnsplashApi.*/


/** NOTE:
 * By defining these network components in a Dagger Hilt module,
 * It becomes easier to manage dependencies and perform dependency injection throughout the app.
 * This can help us write more modular and testable code,
 * and make it easier to maintain and update the network layer of the app.
 * */