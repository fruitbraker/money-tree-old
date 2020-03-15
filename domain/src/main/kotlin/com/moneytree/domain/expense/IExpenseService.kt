package com.moneytree.domain.expense

import com.moneytree.domain.Result
import java.lang.Exception

interface IExpenseService {
    fun search(expenseId: Long): Result<Expense, Exception>
    fun insert(expense: Expense)
}