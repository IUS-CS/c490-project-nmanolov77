package edu.ius.c490.spacexnotifier.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import edu.ius.c490.spacexnotifier.model.Mission
import edu.ius.c490.spacexnotifier.model.MissionApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

private const val TAG = "RocketViewModel"

class RocketViewModel(application: Application) : AndroidViewModel(application) {

    val missions by lazy { MutableLiveData<List<Mission>>() }
    val loading = MutableLiveData<Boolean>()

    private val disposable = CompositeDisposable()
    private val api = MissionApiService()

    fun getMission() {
        loading.value = true
        disposable.add(
            api.getMission()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Mission>>() {
                    override fun onSuccess(t: List<Mission>) {

                        missions.value = t
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                    }
                })
        )
    }
}