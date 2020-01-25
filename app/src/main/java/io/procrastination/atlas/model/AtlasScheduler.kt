package io.procrastination.atlas.model

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AtlasScheduler
@Inject constructor() {

    val io : Scheduler
        get() = Schedulers.io()

    val subscribeOn: Scheduler
        get() = Schedulers.io()

    val observeOn : Scheduler
        get() = AndroidSchedulers.mainThread()

}