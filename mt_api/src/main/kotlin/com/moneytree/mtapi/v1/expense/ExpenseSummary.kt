package com.moneytree.mtapi.v1.expense

import com.moneytree.domain.expense.ExpenseSummary as ExpenseSummaryDomain
import java.time.OffsetDateTime

data class ExpenseSummary(
    val expense_id: Long? = null,
    val transaction_date: OffsetDateTime,
    val transaction_amount: Double,
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
        fun fromDomain(expenseDomain: ExpenseSummaryDomain): ExpenseSummary {
            return ExpenseSummary(
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
