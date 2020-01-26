package io.procrastination.atlas.view.flow

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.procrastination.atlas.R
import io.procrastination.atlas.di.AtlasViewModelFactory
import io.procrastination.atlas.view.list.AtlasListViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var factory: AtlasViewModelFactory

    override fun androidInjector(): AndroidInjector<Any> = injector

    private val viewmodel by viewModels<AtlasListViewModel> { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}