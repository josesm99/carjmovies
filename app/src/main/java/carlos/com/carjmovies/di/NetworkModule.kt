package carlos.com.carjmovies.di

import carlos.com.carjmovies.BuildConfig
import carlos.com.carjmovies.data.network.Api
import carlos.com.carjmovies.data.network.MoviesAppService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { okhttpClient() }
    single { retrofit(get()) }
    single { apiService(get()) }
    single { createMovieAppService(get()) }
}

fun createMovieAppService(
    api: Api
): MoviesAppService = MoviesAppService(api)

fun apiService(
    retrofit: Retrofit
): Api =
    retrofit.create(Api::class.java)

fun retrofit(
    okHttpClient: OkHttpClient
): Retrofit =
    Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

fun okhttpClient(): OkHttpClient =
    OkHttpClient.Builder()
        .build()


