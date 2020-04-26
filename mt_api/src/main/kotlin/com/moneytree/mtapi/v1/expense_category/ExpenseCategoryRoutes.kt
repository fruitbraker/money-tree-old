package com.moneytree.mtapi.v1.expense_category

import com.moneytree.domain.Result
import com.moneytree.domain.expense_category.IExpenseCategoryService
import org.http4k.core.*
import org.http4k.format.Gson.auto
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import org.http4k.routing.routes
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.Exception

@Singleton
class ExpenseCategoryRoutes @Inject constructor(
    private val expenseCategoryService: IExpenseCategoryService
) {

    private val expenseCategoryLens = Body.auto<ExpenseCategory>().toLens()
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
            },
            "/expense_category" bind Method.POST to {
                try {
                    when (expenseCategoryService.insert(expenseCategoryLens(it).expense_category)) {
                        is Result.Ok -> Response(Status.CREATED)
                        is Result.Err -> Response(Status.BAD_REQUEST).body("Bad input data.")
                    }
                } catch (e: Exception) {
                    Response(Status.BAD_REQUEST).body("Bad input data.")
                }
            },
            "/expense_category" bind Method.DELETE to {
                try {
                    val toBeDeleted = expenseCategoryLens(it).expense_category
                    when (expenseCategoryService.delete(toBeDeleted)) {
                        is Result.Ok -> Response(Status.NO_CONTENT)
                        is Result.Err -> Response(Status.NOT_FOUND)
                    }
                } catch (e: Exception) {
                    Response(Status.BAD_REQUEST).body("Bad input data.")
                }
            }
        )
    }
}
