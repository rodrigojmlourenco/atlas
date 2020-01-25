package io.procrastination.atlas.di

import java.lang.annotation.Documented
import javax.inject.Qualifier

@Qualifier
@Documented
@Retention(AnnotationRetention.RUNTIME)
annotation class EndpointQualifier(val value: String = "default")