package com.example.entregaandroidavanzadov2.utils


import com.example.entregaandroidavanzadov2.data.remote.IDragonBallAPI
import com.example.entregaandroidavanzadov2.data.remote.ILoginAPI
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun providesMoshi(): Moshi{
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun providesOkhttp(): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
    }

    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit{

        return Retrofit.Builder()
            .baseUrl("https://dragonball.keepcoding.education/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .build()
    }

    @Provides
    fun providesApi(retrofit: Retrofit): IDragonBallAPI {
        return retrofit.create(IDragonBallAPI::class.java)
    }

    @Provides
    fun providesApiLogin(retrofit: Retrofit): ILoginAPI {
        return  retrofit.create(ILoginAPI::class.java)
    }
}