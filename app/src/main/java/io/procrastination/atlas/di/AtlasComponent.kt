package io.procrastination.atlas.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import io.procrastination.atlas.data.NetworkModule
import io.procrastination.atlas.view.AtlasApplication
import io.procrastination.atlas.view.flow.MainModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        NetworkModule::class,
        MainModule::class
    ]
)
interface AtlasComponent {

    fun inject(app: AtlasApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun setApp(app: AtlasApplication): Builder

        fun build(): AtlasComponent
    }
}