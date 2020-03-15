package com.moneytree.domain.expense

interface IExpenseRepository {
    fun search(expenseId: Long): Expense

    fun insert(expense: Expense)
}