package com.moneytree.domain.expense_category

import com.moneytree.domain.Result
import java.lang.Exception

interface IExpenseCategoryRepository {
    fun get(): Result<List<ExpenseCategory>, Exception>
    fun insert(expenseCategory: String): Result<Unit, Exception>
    fun delete(expenseCategory: String): Result<Unit, Exception>
}
