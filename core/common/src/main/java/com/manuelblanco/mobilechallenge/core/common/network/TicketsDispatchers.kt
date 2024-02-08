package com.manuelblanco.mobilechallenge.core.common.network

import javax.inject.Qualifier

/**
 * Created by Manuel Blanco Murillo on 26/6/23.
 */

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val ticketsDispatcher: TicketsDispatchers)

enum class TicketsDispatchers {
    Default,
    IO,
}