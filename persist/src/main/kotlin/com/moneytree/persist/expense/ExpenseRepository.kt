package com.moneytree.persist.expense

import com.moneytree.domain.expense.Expense
import com.moneytree.domain.expense.IExpenseRepository
import generated.com.moneytree.persist.db.generated.tables.daos.ExpenseDao
import javax.inject.Inject

class ExpenseRepository @Inject constructor(
    private val expenseDao: ExpenseDao
): IExpenseRepository {
    override fun insert(expense: Expense) {

    }
}