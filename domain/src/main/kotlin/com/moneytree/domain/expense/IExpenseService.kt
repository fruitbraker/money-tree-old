package com.moneytree.domain.expense

import javax.inject.Singleton

interface IExpenseService {
    fun search(expenseId: Long): Expense
}