package io.procrastination.atlas.view.details

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CountryDetailsModule {

    @ContributesAndroidInjector
    abstract fun bindCountryDetailsFragment(): CountryDetailsFragment
}