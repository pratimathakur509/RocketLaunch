package rocketlaunch.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import rocketlaunch.data.api.ApiHelper
import rocketlaunch.data.repository.DataRepository
import rocketlaunch.ui.main.viewmodel.RocketLaunchViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RocketLaunchViewModel::class.java)) {
            return RocketLaunchViewModel(DataRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}