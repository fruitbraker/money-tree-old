package com.moneytree.mtapi.v1

import com.moneytree.mtapi.v1.expense.Expense
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

fun main() {
    val test = mutableListOf<testData>()
    test.add(testData("one", 1L))
    test.add(testData("two", 2L))
    test.add(testData("three", 3L))

    val testBodyData = testData("test", 123L)
    val testBody = Body.auto<testData>().toLens()
    val testBodies = Body.auto<MutableList<testData>>().toLens()

    val expenses = mutableListOf<Expense>()
    expenses.add(Expense("Mcdonalds", 1.99))
    expenses.add(Expense("Taco Bell", 14.49))
    expenses.add(Expense("Micro Center", 9.98))

    val app = routes(
        "/health" bind GET to { Response(OK).body("Your money tree is alive and growing cash!") },
        "/test" bind GET to { Response(OK).with(testBody of testBodyData) },
        "/tests" bind GET to { Response(OK).with(testBodies of test) },
        "/expenses" bind GET to { Response(OK).with(Expense.expenseLens of expenses) }
    )

    val jettyServer = app.asServer(Jetty(9000)).start()
}

data class testData(
    val name: String,
    val id: Long
)
