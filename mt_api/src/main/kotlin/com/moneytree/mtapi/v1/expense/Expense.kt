package com.moneytree.mtapi.v1.expense

import org.http4k.core.Body
import org.http4k.format.Gson.auto
import org.http4k.lens.BiDiBodyLensSpec

data class Expense (
    val expense_id: String,
    val amount: Double
) {
    companion object {
        val expenseLens = Body.auto<MutableList<Expense>>().toLens()

    }
}