package com.moneytree.domain.expense

interface IExpenseRepository {
    fun search(expenseId: Long): Expense
}