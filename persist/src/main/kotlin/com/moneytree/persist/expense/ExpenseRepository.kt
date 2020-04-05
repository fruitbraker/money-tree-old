package com.moneytree.persist.expense

import com.moneytree.domain.expense.ExpenseSummary
import com.moneytree.domain.expense.IExpenseRepository
import com.moneytree.domain.Result
import com.moneytree.domain.expense.Expense
import com.moneytree.domain.toErr
import com.moneytree.domain.toOk
import com.moneytree.persist.db.generated.Tables.*
import org.jooq.DSLContext
import org.jooq.Record
import java.lang.Exception
import javax.inject.Inject

class ExpenseRepository @Inject constructor(
    private val dslContext: DSLContext
): IExpenseRepository {

    private fun Record.toDomain(): ExpenseSummary {
        return ExpenseSummary(
            expenseId = this[EXPENSE.EXPENSE_ID],
            transactionDate = this[EXPENSE.TRANSACTION_DATE],
            transactionAmount = this[EXPENSE.TRANSACTION_AMOUNT].toDouble(),
            vendorId = this[EXPENSE.VENDOR_ID],
            vendorName = this[VENDOR.VENDOR_NAME],
            expenseCategory = this[EXPENSE.EXPENSE_CATEGORY],
            metadataId = this[EXPENSE.METADATA_ID],
            dateCreated = this[METADATA.DATE_CREATED],
            dateModified = this[METADATA.DATE_MODIFIED],
            notes = this[METADATA.NOTES],
            hide = this[EXPENSE.HIDE]
        )
    }

    override fun search(expenseId: Long): Result<ExpenseSummary, Exception> {
         val result = dslContext.configuration().dsl()
            .select()
            .from(EXPENSE)
            .join(VENDOR).on(EXPENSE.VENDOR_ID.eq(VENDOR.VENDOR_ID))
            .join(METADATA).on(EXPENSE.METADATA_ID.eq(METADATA.METADATA_ID))
            .where(EXPENSE.EXPENSE_ID.eq(expenseId))
            .fetch()


        return try {
            result.mapNotNull { it.toDomain() }.first().toOk()
        } catch (e: Exception) {
            e.toErr()
        }
    }

    override fun insert(expense: Expense): Result<Long, Exception> {
        return try {
            val result = dslContext.configuration().dsl()
                .insertInto(EXPENSE)
                .columns(EXPENSE.TRANSACTION_DATE,
                    EXPENSE.TRANSACTION_AMOUNT,
                    EXPENSE.VENDOR_ID,
                    EXPENSE.EXPENSE_CATEGORY,
                    EXPENSE.METADATA_ID,
                    EXPENSE.HIDE)
                .values(expense.transactionDate,
                    expense.transactionAmount,
                    expense.vendorId,
                    expense.expenseCategory,
                    expense.metadataId,
                    expense.hide)
                .returning(EXPENSE.EXPENSE_ID)
                .fetch()
            result.mapNotNull { it }.first()[EXPENSE.EXPENSE_ID].toOk()
        } catch (e: Exception) {
            e.toErr()
        }
    }
}