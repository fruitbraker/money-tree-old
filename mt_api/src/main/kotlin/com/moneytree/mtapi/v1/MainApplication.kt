package com.moneytree.mtapi.v1

import com.google.inject.AbstractModule
import com.google.inject.Guice
import com.google.inject.Injector
import com.moneytree.domain.expense.ExpenseService
import com.moneytree.domain.expense.IExpenseService
import com.moneytree.domain.expense_category.ExpenseCategoryService
import com.moneytree.domain.expense_category.IExpenseCategoryService
import com.moneytree.mtapi.v1.expense.ExpenseRoutes
import com.moneytree.mtapi.v1.expense_category.ExpenseCategoryRoutes
import com.moneytree.persist.PersistModules
import org.http4k.core.Method.GET
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.asServer
import org.http4k.routing.RoutingHttpHandler
import org.http4k.server.Http4kServer

fun main() {
    setUpServer().start()
}

fun setUpServer(): Http4kServer {
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

    val allRoutes = routes(health(),
        injector.getInstance(ExpenseRoutes::class.java).expenseRoutes(),
        injector.getInstance(ExpenseCategoryRoutes::class.java).expenseCategoryRoutes()
    )

    return allRoutes.asServer(org.http4k.server.Jetty(9000))
}

class ServiceModules : AbstractModule() {
    override fun configure() {
        bind(IExpenseService::class.java).to(ExpenseService::class.java).asEagerSingleton()
        bind(IExpenseCategoryService::class.java).to(ExpenseCategoryService::class.java).asEagerSingleton()
    }
}

class RouteModules : AbstractModule() {
    override fun configure() {
        bind(ExpenseRoutes::class.java).asEagerSingleton()
        bind(ExpenseCategoryRoutes::class.java).asEagerSingleton()
    }
}
