package com.moneytree.domain.expense

import java.sql.Timestamp

data class Expense (
    val expenseId: Long?,
    val transactionDate: Timestamp,
    val transactionAmount: Double,
    val vendorId: Long,
    val expenseCategory: String,
    val metadata: Long?,
    val hide: Boolean
)