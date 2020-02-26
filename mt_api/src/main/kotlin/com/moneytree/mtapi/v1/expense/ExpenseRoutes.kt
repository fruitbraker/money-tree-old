package com.moneytree.mtapi.v1.expense

import com.moneytree.domain.expense.IExpenseService
import org.http4k.core.*
import org.http4k.format.Gson.auto
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.routes
import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.lens.Path
import org.http4k.lens.string
import org.http4k.routing.bind
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExpenseRoutes @Inject constructor(private val expenseService: IExpenseService) {
    fun expenseRoutes (): RoutingHttpHandler {
        val expenseLens = Body.auto<Expense>().toLens()
        val expenseListLens = Body.auto<MutableList<Expense>>().toLens()
        val expenseIdLens = Path.string().of("expense_id")

        return routes(
            "/expense/{expense_id}" bind GET to {request: Request ->
                val expenseId = expenseIdLens(request).toLong()
                expenseService.search(expenseId)
                Response(Status.OK).body("Retrieved expense with id $expenseId")
            },
            "/expense" bind POST to { request: Request ->
                println(request.toMessage())
                Response(Status.CREATED).header("New Expense", "something")
            }
        )
    }
}

//@Inject
//fun expenseRoutes (expenseService: IExpenseService): RoutingHttpHandler {
//    val expenseLens = Body.auto<Expense>().toLens()
//    val expenseListLens = Body.auto<MutableList<Expense>>().toLens()
//    val expenseIdLens = Path.string().of("expense_id")
//
//    return routes(
//        "/expense/{expense_id}" bind GET to {request: Request ->
//            val expenseId = expenseIdLens(request).toLong()
//            expenseService.search(expenseId)
//            Response(Status.OK).body("Retrieved expense with id ${expenseId}")
//        },
//        "/expense" bind POST to { request: Request ->
//            println(request.toMessage())
//            Response(Status.CREATED).header("New Expense", "something")
//        }
//    )
//}