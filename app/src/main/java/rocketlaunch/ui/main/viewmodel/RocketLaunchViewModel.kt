package rocketlaunch.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import rocketlaunch.data.repository.DataRepository
import rocketlaunch.util.ApiUtil.Resource
import kotlinx.coroutines.Dispatchers

class RocketLaunchViewModel(private val mainRepository: DataRepository) : ViewModel() {

    fun getUsers() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getRocketLaunchData()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}