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
                val result = Expense.fromDomain(expenseService.search(expenseId))
                Response(Status.OK).with(expenseLens of result)
            },
            "/expense" bind POST to { request: Request ->
                val expense = expenseLens(request)

                Response(Status.CREATED).header("New Expense", "something")
            }
        )
    }
}
