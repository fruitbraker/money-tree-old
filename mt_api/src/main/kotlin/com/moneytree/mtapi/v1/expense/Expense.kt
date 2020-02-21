package com.moneytree.mtapi.v1.expense

import org.http4k.core.Body
import org.http4k.format.Gson.auto
import java.sql.Timestamp
import com.moneytree.domain.expense.Expense as ExpenseDomain

data class Expense (
    val expense_id: Long? = null,
    val transaction_date: Timestamp,
    val transaction_amount: Double,
    val vendor_id: Long,
    val expense_category: String,
    val metadata: Long? = null,
    val hide: Boolean
) {
    companion object {
        fun toDomain(expense: Expense): ExpenseDomain {
            return ExpenseDomain(
                expenseId = expense.expense_id,
                transactionDate = expense.transaction_date,
                transactionAmount = expense.transaction_amount,
                vendorId = expense.vendor_id,
                expenseCategory = expense.expense_category,
                metadata = expense.metadata,
                hide = expense.hide
            )
        }
    }
}