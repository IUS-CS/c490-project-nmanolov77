package edu.ius.c490.spacexnotifier

import org.json.JSONObject

class SpacexResponse {
    lateinit var launchItemLiveData: List<JSONObject>
    init {
        launchItemLiveData = FlickrFetchr().fetchLaunches()
    }

}