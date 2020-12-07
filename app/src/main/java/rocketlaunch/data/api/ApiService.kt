package rocketlaunch.data.api

import rocketlaunch.data.model.Patch
import rocketlaunch.data.model.RocketLaunchData
import retrofit2.http.GET

interface ApiService {
    @GET("launches")
    suspend fun getRocketLaunchData() : List<RocketLaunchData>
}