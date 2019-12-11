package edu.ius.c490.spacexnotifier.model

import edu.ius.c490.spacexnotifier.model.Mission
import io.reactivex.Single
import retrofit2.http.GET

interface MissionApi {

    @GET("launches/upcoming?")
    fun getMissions(): Single<List<Mission>>
}