package io.procrastination.atlas.view.list

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ListModule {

    @ContributesAndroidInjector
    abstract fun bindListFragment() : ListFragment
}