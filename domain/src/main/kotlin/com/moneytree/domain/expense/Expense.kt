package com.moneytree.domain.expense

import java.math.BigDecimal
import java.sql.Timestamp

data class Expense (
    val expenseId: Long?,
    val transactionDate: Timestamp,
    val transactionAmount: BigDecimal,
    val vendorId: Long,
    val vendorName: String,
    val expenseCategory: String,
    val metadataId: Long,
    val dateCreated: Timestamp,
    val dateModified: Timestamp,
    val notes: String?,
    val hide: Boolean
)
