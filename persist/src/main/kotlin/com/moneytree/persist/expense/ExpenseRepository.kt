package com.moneytree.persist.expense

import com.moneytree.domain.expense.Expense
import com.moneytree.domain.expense.IExpenseRepository
import com.moneytree.domain.Result
import com.moneytree.domain.toErr
import com.moneytree.domain.toOk
import com.moneytree.persist.db.generated.Tables.METADATA
import com.moneytree.persist.db.generated.Tables.VENDOR
import com.moneytree.persist.db.generated.tables.Expense.EXPENSE
import org.jooq.DSLContext
import org.jooq.Record
import java.lang.Exception
import javax.inject.Inject

class ExpenseRepository @Inject constructor(
    private val dslContext: DSLContext
): IExpenseRepository {

    private fun Record.toDomain(): Expense {
        return Expense(
            expenseId = this[EXPENSE.EXPENSE_ID],
            transactionDate = this[EXPENSE.TRANSACTION_DATE],
            transactionAmount = this[EXPENSE.TRANSACTION_AMOUNT],
            vendorId = this[EXPENSE.VENDOR_ID],
            vendorName = this[VENDOR.VENDOR_NAME],
            expenseCategory = this[EXPENSE.EXPENSE_CATEGORY],
            metadataId = this[EXPENSE.METADATA],
            dateCreated = this[METADATA.DATE_CREATED],
            dateModified = this[METADATA.DATE_MODIFIED],
            notes = this[METADATA.NOTES],
            hide = this[EXPENSE.HIDE]
        )
    }

    override fun search(expenseId: Long): Result<Expense, Exception> {
        val result = dslContext.configuration().dsl()
            .select()
            .from(EXPENSE)
            .join(VENDOR).on(EXPENSE.VENDOR_ID.eq(VENDOR.VENDOR_ID))
            .join(METADATA).on(EXPENSE.METADATA.eq(METADATA.METADATA_ID))
            .where(EXPENSE.EXPENSE_ID.eq(expenseId))
            .fetch()


        return try {
            result.mapNotNull { it.toDomain() }.first().toOk()
        } catch (e: Exception) {
            e.toErr()
        }
    }

    override fun insert(expense: Expense) {

    }
}