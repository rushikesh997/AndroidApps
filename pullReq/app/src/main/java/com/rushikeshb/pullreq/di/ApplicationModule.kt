package com.rushikeshb.pullreq.di

import com.rushikeshb.pullreq.model.repository.PullRequestService
import com.rushikeshb.pullreq.model.repository.PullRequestServiceHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    fun providesPullRequestService(): PullRequestService {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(PullRequestService::class.java)
    }

    @Provides
    fun providesPullRequestServiceHelper(service: PullRequestService): PullRequestServiceHelper {
        return PullRequestServiceHelper(service)
    }

    companion object {
        private const val BASE_URL = "https://api.github.com/"
    }
}
