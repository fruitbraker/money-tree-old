package com.moneytree.persist.expense

import com.moneytree.domain.expense.Expense
import com.moneytree.domain.expense.IExpenseRepository
import generated.com.moneytree.persist.db.generated.tables.Expense.EXPENSE
import org.jooq.DSLContext
import org.jooq.Record
import javax.inject.Inject

class ExpenseRepository @Inject constructor(
    private val dslContext: DSLContext
): IExpenseRepository {

    fun Record.toDomain(record: Record) {

    }

    override fun search(expenseId: Long) {
        val result = dslContext.configuration().dsl()
            .select(EXPENSE.asterisk())
            .from(EXPENSE)
            .where(EXPENSE.EXPENSE_ID.eq(expenseId))
            .fetch()
        return
    }
}