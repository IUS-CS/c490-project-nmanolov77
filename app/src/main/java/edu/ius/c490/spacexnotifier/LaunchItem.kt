package edu.ius.c490.spacexnotifier

import com.google.gson.annotations.SerializedName

class LaunchItem {

    @SerializedName("mission_name")
    lateinit var missionName: String

    @SerializedName("launch_date_local")
    lateinit var launchDate: String

    @SerializedName("rocket_name")
    lateinit var rocketName: String

    @SerializedName("launch_site")
    lateinit var synonymDatamuseWords: String


}
