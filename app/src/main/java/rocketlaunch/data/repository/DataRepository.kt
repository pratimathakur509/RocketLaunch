package rocketlaunch.data.repository

import rocketlaunch.data.api.ApiHelper


class DataRepository(private val apiHelper: ApiHelper) {

    suspend fun getRocketLaunchData() = apiHelper.getRocketLaunchData()
}