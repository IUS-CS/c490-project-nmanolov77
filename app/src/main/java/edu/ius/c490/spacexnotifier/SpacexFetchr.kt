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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val TAG = "FlickrFetchr"

class SpacexFetchr {
    private val spacexApi: SpacexApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.flickr.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        spacexApi = retrofit.create(SpacexApi::class.java)
    }

    fun fetchContents(): LiveData<String> {
        val responseLiveData: MutableLiveData<String> = MutableLiveData()
        val flickrRequest = spacexApi.fetchContents()

        flickrRequest.enqueue(object: Callback<String> {
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
    fun fetchPhotos(): LiveData<List<GalleryItem>> {
        val responseLiveData: MutableLiveData<List<GalleryItem>> = MutableLiveData()
        flickrApi.fetchPhotos().enqueue(object: Callback<FlickrResponse> {
            override fun onResponse(
                call: Call<FlickrResponse>,
                response: Response<FlickrResponse>
            ) {
                val flickrResponse = response.body()
                val photoResponse = flickrResponse?.photos
                Log.d(TAG, "Number of photos: ${photoResponse?.total}")
                var galleryItems = photoResponse?.galleryItems
                    ?: mutableListOf()
                galleryItems = galleryItems.filterNot {
                    it.url.isBlank()
                }
                responseLiveData.value = galleryItems
            }

            override fun onFailure(call: Call<FlickrResponse>, t: Throwable) {
                Log.d(TAG, "$t")
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