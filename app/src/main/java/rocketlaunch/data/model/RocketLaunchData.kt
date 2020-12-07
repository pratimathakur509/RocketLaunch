package rocketlaunch.data.model

import com.google.gson.annotations.SerializedName


data class RocketLaunchData (
        val id: String,
        val success : Boolean,
        val name : String,
        @SerializedName("date_utc")
        val date : String,
        val links : Links?
)

data class Links(
        val patch: Patch?
)
data class Patch(
        @SerializedName("small")
        val smallImage: String
)

