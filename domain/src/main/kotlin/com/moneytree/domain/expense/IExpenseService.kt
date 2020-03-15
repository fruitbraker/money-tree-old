package com.moneytree.domain.expense

interface IExpenseService {
    fun search(expenseId: Long): Expense
    fun insert(expense: Expense)
}