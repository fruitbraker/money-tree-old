package com.moneytree.mtapi.v1.expense

import org.http4k.core.*
import org.http4k.format.Gson.auto
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.routes
import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.routing.bind


fun expenseRoutes(): RoutingHttpHandler {
    val expenseLens = Body.auto<Expense>().toLens()
    val expenseListLens = Body.auto<MutableList<Expense>>().toLens()

    return routes(
        "/expense" bind POST to { request: Request ->
            println(request.toMessage())
            Response(Status.CREATED).header("New Expense", "something")
        }
    )
}