package io.procrastination.atlas.view

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.procrastination.atlas.di.DaggerAtlasComponent
import javax.inject.Inject

class AtlasApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var injector : DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = injector

    override fun onCreate() {
        super.onCreate()

        DaggerAtlasComponent
            .builder()
            .setApp(this)
            .build()
            .inject(this)
    }
}