package com.moneytree.mtapi.v1.expense_category

import com.moneytree.domain.expense_category.IExpenseCategoryService
import org.http4k.core.*
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import org.http4k.routing.routes
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExpenseCategoryRoutes @Inject constructor(
    private val expenseCategoryService: IExpenseCategoryService
) {
    fun expenseCategoryRoutes(): RoutingHttpHandler {
        return routes(
            "/expense_category" bind Method.GET to { request: Request ->
                Response(Status.OK).body("Expense Category OK")
            }
        )
    }
}