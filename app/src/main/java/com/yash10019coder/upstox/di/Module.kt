package com.yash10019coder.upstox.di


import com.google.gson.Gson
import com.yash10019coder.upstox.data.BaseUrl
import com.yash10019coder.upstox.data.api.StockService
import com.yash10019coder.upstox.data.repository.StocksRepo
import com.yash10019coder.upstox.data.repository.StocksRepoImpl
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
class Module {
    @Provides
    @BaseUrl
    fun provideBaseUrl() = "https://35dee773a9ec441e9f38d5fc249406ce.api.mockbin.io"

    @Provides
    fun provideOkhttpClient(): OkHttpClient {
        lateinit var loggingInteceptor: HttpLoggingInterceptor
        loggingInteceptor = HttpLoggingInterceptor()
        loggingInteceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .callTimeout(5, java.util.concurrent.TimeUnit.SECONDS)
            .addInterceptor(loggingInteceptor)
            .readTimeout(5, java.util.concurrent.TimeUnit.SECONDS)
            .writeTimeout(5, java.util.concurrent.TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideRetrofit(
        @BaseUrl baseUrl: String,
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun provideGson(): Gson {
        return Gson()
            .newBuilder()
            .setLenient()
            .create()
    }

    @Provides
    fun provideStockService(retrofit: Retrofit): StockService {
        return retrofit.create(StockService::class.java)
    }

    @Provides
    fun provideStockRepository(
        stockService: StockService
    ): StocksRepo {
        return StocksRepoImpl(stockService)
    }
}
