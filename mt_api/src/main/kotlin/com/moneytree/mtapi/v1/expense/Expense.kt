package com.moneytree.mtapi.v1.expense

import com.moneytree.domain.expense.Expense as ExpenseDomain
import java.math.BigDecimal
import java.time.OffsetDateTime

data class Expense(
    val expense_id: Long? = null,
    val transaction_date: OffsetDateTime,
    val transaction_amount: BigDecimal,
    val vendor_id: Long,
    val expense_category: String,
    val metadata_id: Long,
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
                metadataId = expense.metadata_id,
                hide = expense.hide
            )
        }
    }
}
