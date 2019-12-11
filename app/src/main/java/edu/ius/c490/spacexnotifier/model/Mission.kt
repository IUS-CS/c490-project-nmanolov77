package edu.ius.c490.spacexnotifier.model

data class Rocket(
    val rocket_name: String
)

data class LaunchSite(
    val site_name: String
)

data class Mission(
    val mission_name: String,
    val launch_date_unix: Int,
    val launch_date_utc: String,
    val launch_date_local: String,
    val rocket: Rocket,
    val launch_site: LaunchSite,
    val details: String
)

