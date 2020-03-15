package com.moneytree.mtapi.v1.expense

import org.http4k.core.Body
import org.http4k.format.Gson.auto
import java.math.BigDecimal
import java.sql.Timestamp
import java.time.OffsetDateTime
import kotlin.math.exp
import com.moneytree.domain.expense.Expense as ExpenseDomain

data class Expense (
    val expense_id: Long? = null,
    val transaction_date: OffsetDateTime,
    val transaction_amount: BigDecimal,
    val vendor_id: Long,
    val vendor_name: String,
    val expense_category: String,
    val metadata_id: Long,
    val date_created: OffsetDateTime,
    val date_modified: OffsetDateTime,
    val notes: String? = null,
    val hide: Boolean
) {
    companion object {
        fun toDomain(expense: Expense): ExpenseDomain {
            return ExpenseDomain(
                expenseId = expense.expense_id,
                transactionDate = expense.transaction_date,
                transactionAmount = expense.transaction_amount,
                vendorId = expense.vendor_id,
                vendorName = expense.vendor_name,
                expenseCategory = expense.expense_category,
                metadataId = expense.metadata_id,
                dateCreated = expense.date_created,
                dateModified = expense.date_modified,
                notes = expense.notes,
                hide = expense.hide
            )
        }

        fun fromDomain(expenseDomain: ExpenseDomain): Expense {
            return Expense(
                expense_id = expenseDomain.expenseId,
                transaction_date = expenseDomain.transactionDate,
                transaction_amount = expenseDomain.transactionAmount,
                vendor_id = expenseDomain.vendorId,
                vendor_name = expenseDomain.vendorName,
                expense_category = expenseDomain.expenseCategory,
                metadata_id = expenseDomain.metadataId,
                date_created = expenseDomain.dateCreated,
                date_modified = expenseDomain.dateModified,
                notes = expenseDomain.notes,
                hide = expenseDomain.hide
            )
        }
    }
}