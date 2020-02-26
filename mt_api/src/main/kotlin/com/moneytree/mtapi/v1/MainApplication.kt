package com.moneytree.mtapi.v1

import com.google.inject.AbstractModule
import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.Provides
import com.moneytree.domain.expense.ExpenseService
import com.moneytree.domain.expense.IExpenseRepository
import com.moneytree.domain.expense.IExpenseService
import com.moneytree.mtapi.v1.expense.ExpenseRoutes
import com.moneytree.persist.PersistModules
import org.http4k.core.Body
import org.http4k.core.Method.GET
import org.http4k.server.Jetty
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.with
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.asServer
import org.http4k.format.Gson.auto
import org.http4k.routing.RoutingHttpHandler
import java.sql.Connection
import java.sql.DriverManager
import javax.sql.DataSource

fun main() {
    fun health(): RoutingHttpHandler {
        return routes(
            "/health" bind GET to { Response(OK).body("Your money tree is growing some nice cash. Hopefully Uncle Sam won't take too much but who cares? You'll get to retire early.") }
        )
    }

    val injector: Injector = Guice.createInjector(
        ServiceModules(),
        RouteModules(),
        PersistModules()
    )

    val allRoutes = routes(health(), injector.getInstance(ExpenseRoutes::class.java).expenseRoutes())

    allRoutes.asServer(Jetty(9000)).start()
}

class ServiceModules: AbstractModule() {
    override fun configure() {
        bind(IExpenseService::class.java).to(ExpenseService::class.java).asEagerSingleton()
    }
}

class RouteModules: AbstractModule() {
    override fun configure() {
        bind(ExpenseRoutes::class.java).asEagerSingleton()
    }
}


