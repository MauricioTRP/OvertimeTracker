package com.kotlinpl.core.data

import kotlin.annotation.AnnotationRetention.RUNTIME
import javax.inject.Qualifier

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val ottDispatcher: OttDispatchers)

enum class OttDispatchers {
    DEFAULT,
    IO
}