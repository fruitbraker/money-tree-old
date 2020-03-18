package com.moneytree.mtapi.v1.expense

import java.math.BigDecimal
import java.time.OffsetDateTime
import com.moneytree.domain.expense.Expense as ExpenseDomain

data class Expense (
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
            return com.moneytree.domain.expense.Expense(
                expenseId = expense.expense_id,
                transactionDate = expense.transaction_date,
                transactionAmount = expense.transaction_amount,
                vendorId = expense.vendor_id,
                expenseCategory = expense.expense_category,
                metadataId = expense.metadata_id,
                hide = expense.hide
            )
        }

        fun fromDomain(expenseDomain: ExpenseDomain): Expense {
            return Expense(
                expense_id = expenseDomain.expenseId,
                transaction_date = expenseDomain.transactionDate,
                transaction_amount = expenseDomain.transactionAmount,
                vendor_id = expenseDomain.vendorId,
                expense_category = expenseDomain.expenseCategory,
                metadata_id = expenseDomain.metadataId,
                hide = expenseDomain.hide
            )
        }
    }
}