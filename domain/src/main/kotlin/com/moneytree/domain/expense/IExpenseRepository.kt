package com.moneytree.domain.expense

interface IExpenseRepository {
    fun insert(expense: Expense)
}