package edu.ius.c490.spacexnotifier.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MissionApiService {

    private val BASE_URL = "https://api.spacexdata.com/v3/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(MissionApi::class.java)

    fun getMission(): Single<List<Mission>> {
        return api.getMissions()
    }
}