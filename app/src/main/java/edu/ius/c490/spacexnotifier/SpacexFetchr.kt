package edu.ius.c490.spacexnotifier

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.Gallery
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import edu.ius.c490.spacexnotifier.api.SpacexApi
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val TAG = "SpacexFetchr"

class SpacexFetchr {
    private val spacexApi: SpacexApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spacexdata.com/v3/launches/upcoming?")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        spacexApi = retrofit.create(SpacexApi::class.java)
    }

    fun fetchLaunches(): LiveData<String>  {
        val responseLiveData: MutableLiveData<String> = MutableLiveData()
        val spacexRequest = spacexApi.fetchContents()

        spacexRequest.enqueue(object: Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e(TAG, "Request failed: ${t}")
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.d(TAG, "Response received")
                responseLiveData.value = response.body()
            }

        })

        return responseLiveData
    }

    @WorkerThread
    fun fetchPhoto(url: String): Bitmap? {
        val resp: Response<ResponseBody> = flickrApi.fetchUrlBytes(url).execute()
        val bitmap = resp.body()?.byteStream()?.use(BitmapFactory::decodeStream)
        return bitmap
    }
}