package com.moneytree.domain.expense

import com.moneytree.domain.Result
import java.lang.Exception

interface IExpenseRepository {
    fun search(expenseId: Long): Result<ExpenseSummary, Exception>
    fun insert(expense: Expense): Result<Long, Exception>
}