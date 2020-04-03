package com.moneytree.mtapi.v1.expense

import com.moneytree.domain.Result
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
        val expenseSummaryLens = Body.auto<ExpenseSummary>().toLens()
        val expenseSummaryListLens = Body.auto<MutableList<ExpenseSummary>>().toLens()
        val expenseIdLens = Path.string().of("expense_id")

        return routes(
            "/expense/{expense_id}" bind GET to {request: Request ->
                try {
                    val expenseId = expenseIdLens(request).toLong()
                    when (val result = expenseService.search(expenseId)) {
                        is Result.Ok -> {
                            Response(Status.OK).with(expenseSummaryLens of ExpenseSummary.fromDomain(result.value))
                        }
                        is Result.Err -> {
                            when (result.error) {
                                is java.util.NoSuchElementException -> Response(Status.NOT_FOUND).body("Expense not found.")
                                else -> Response(Status.BAD_REQUEST)
                            }
                        }
                    }
                } catch (e: Exception) {
                    Response(Status.BAD_REQUEST).body("Bad input data.")
                }
            },
            "/expense" bind POST to { request: Request ->
                try {
                    val newExpense = expenseLens(request)
                    when (val result = expenseService.insert(Expense.toDomain(newExpense))) {
                        is Result.Ok -> Response(Status.CREATED).with(expenseLens of newExpense.copy(expense_id = result.value))
                        is Result.Err -> Response(Status.BAD_REQUEST).body("Bad input data.")
                    }
                } catch (e: Exception) {
                    Response(Status.BAD_REQUEST).body("Bad input data.")
                }
            }
        )
    }
}
