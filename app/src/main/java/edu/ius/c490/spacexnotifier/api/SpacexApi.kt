package edu.ius.c490.spacexnotifier.api

import edu.ius.c490.spacexnotifier.BuildConfig
import edu.ius.c490.spacexnotifier.SpacexResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface SpacexApi {
    @GET("/")
    fun fetchContents(): retrofit2.Call<String>

    @GET("https://api.spacexdata.com/v3/launches/upcoming?" +
        "&format=json" +
        "&nojsoncallback=1" +
        "&extras=url_s")
    fun fetchPhotos(): Call<SpacexResponse>

    @GET
    fun fetchUrlBytes(@Url url: String): Call<ResponseBody>
}
