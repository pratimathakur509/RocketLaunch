package rocketlaunch.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getRocketLaunchData() = apiService.getRocketLaunchData()

}