package io.procrastination.atlas.view.flow

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import io.procrastination.atlas.di.ViewModelKey
import io.procrastination.atlas.view.details.CountryDetailsModule
import io.procrastination.atlas.view.details.CountryDetailsViewModel
import io.procrastination.atlas.view.list.AtlasListViewModel
import io.procrastination.atlas.view.list.ListModule

@Module
abstract class MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(AtlasListViewModel::class)
    abstract fun bindAtlasListViewModel(viewmodel: AtlasListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CountryDetailsViewModel::class)
    abstract fun bindCountryDetailsViewModel(viewmodel: CountryDetailsViewModel): ViewModel

    @ContributesAndroidInjector(
        modules = [
            ListModule::class,
            CountryDetailsModule::class]
    )
    abstract fun bindMainActivity(): MainActivity
}