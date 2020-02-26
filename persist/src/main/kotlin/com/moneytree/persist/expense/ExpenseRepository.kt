package com.moneytree.persist.expense

import com.moneytree.domain.expense.Expense
import com.moneytree.domain.expense.IExpenseRepository
import generated.com.moneytree.persist.db.generated.tables.Expense.EXPENSE
import generated.com.moneytree.persist.db.generated.tables.daos.ExpenseDao
import javax.inject.Inject

class ExpenseRepository @Inject constructor(
    private val expenseDao: ExpenseDao
): IExpenseRepository {
    override fun search(expenseId: Long) {
        val result = expenseDao.configuration().dsl()
            .select(EXPENSE.asterisk())
            .from(EXPENSE)
            .where(EXPENSE.EXPENSE_ID.eq(expenseId))
            .execute()
        return
    }
}