package com.moneytree.mtapi.v1.expense_category

import com.moneytree.domain.Result
import com.moneytree.domain.expense_category.IExpenseCategoryService
import org.http4k.core.*
import org.http4k.format.Gson.auto
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import org.http4k.routing.routes
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExpenseCategoryRoutes @Inject constructor(
    private val expenseCategoryService: IExpenseCategoryService
) {
    private val expenseCategoryListLens = Body.auto<List<ExpenseCategory>>().toLens()

    fun expenseCategoryRoutes(): RoutingHttpHandler {
        return routes(
            "/expense_category" bind Method.GET to {
                try {
                    when (val result = expenseCategoryService.get()) {
                        is Result.Ok -> {
                            Response(Status.OK).with(
                                expenseCategoryListLens of result.value.map { ExpenseCategory.fromDomain(it) }
                            )
                        }
                        is Result.Err -> {
                            Response(Status.BAD_GATEWAY).body("Something terrible went wrong. Contact developer.")
                        }
                    }
                } catch (e: Exception) {
                    Response(Status.BAD_GATEWAY).body("Something terrible went wrong. Contact developer.")
                }
            }
        )
    }
}