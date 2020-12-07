package rocketlaunch.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val BASE_URL = "https://api.spacexdata.com/v4/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build() //Doesn't require the adapter
    }
    val apiService: ApiService = getRetrofit().create(ApiService::class.java)

}