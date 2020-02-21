package com.moneytree.mtapi.v1

import com.moneytree.mtapi.v1.expense.Expense
import com.moneytree.mtapi.v1.expense.expenseRoutes
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

fun main() {

    fun health(): RoutingHttpHandler {
        return routes(
            "/health" bind GET to { Response(OK).body("Your money tree is growing some nice cash. Hopefully Uncle Sam won't too much but who cares? You'll get to retire early.") }
        )
    }

    val allRoutes = routes(health(), expenseRoutes())

    allRoutes.asServer(Jetty(9000)).start()
}
