package io.procrastination.atlas.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import io.procrastination.atlas.data.NetworkModule
import io.procrastination.atlas.view.AtlasApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        NetworkModule::class
    ]
)
interface AtlasComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun setApp(app: AtlasApplication): Builder

        fun build(): AtlasComponent
    }
}