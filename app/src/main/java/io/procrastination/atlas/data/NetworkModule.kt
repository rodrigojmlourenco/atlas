package io.procrastination.atlas.data

import dagger.Module
import dagger.Provides
import io.procrastination.atlas.di.EndpointQualifier
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber

@Module
class NetworkModule {

    @Provides
    @EndpointQualifier
    fun provideEndpoint(): String {
        return "https://restcountries.eu/rest/v2/"
    }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.tag("NET").i(message)
            }
        })

        interceptor.level = HttpLoggingInterceptor.Level.BODY


        return interceptor
    }

    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        @EndpointQualifier endpoint: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(endpoint)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun provideAtlasService(retrofit: Retrofit) : AtlasService {
        return retrofit.create(AtlasService::class.java)
    }
}