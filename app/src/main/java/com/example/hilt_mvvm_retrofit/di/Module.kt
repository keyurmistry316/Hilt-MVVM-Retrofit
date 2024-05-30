package com.example.hilt_mvvm_retrofit.di

import android.content.Context
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.room.Room
import com.example.hilt_mvvm_retrofit.db.Dao
import com.example.hilt_mvvm_retrofit.db.ProductDB
import com.example.hilt_mvvm_retrofit.retrofit.FakerAPI
import com.example.hilt_mvvm_retrofit.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Module {

    @Singleton
    @Provides
    fun provideRetrofit(httpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    @Singleton
    @Provides
    fun providesApi(retrofit: Retrofit):FakerAPI{
        return retrofit.create(FakerAPI::class.java)
    }

    @Singleton
    @Provides
    fun getClient(loggingInterceptor: HttpLoggingInterceptor):OkHttpClient{

        return OkHttpClient().newBuilder()
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
        }

    @Singleton
    @Provides
    fun getHttpLoggingInterceptor():HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun getDatabase(@ApplicationContext context: Context):ProductDB{
        return Room.databaseBuilder(
            context,
            ProductDB::class.java,
            "product_db"
        ).build()
    }

    @Singleton
    @Provides
    fun getDao(productDB: ProductDB): Dao {
        return productDB.getDao()
    }

    @Singleton
    @Provides
    fun getNetworkRequestBuilder(): NetworkRequest{
        return  NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()
    }

}