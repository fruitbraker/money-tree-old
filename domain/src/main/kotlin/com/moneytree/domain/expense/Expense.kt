package com.moneytree.domain.expense

import java.math.BigDecimal
import java.time.OffsetDateTime

data class Expense(
    val expenseId: Long?,
    val transactionDate: OffsetDateTime,
    val transactionAmount: BigDecimal,
    val vendorId: Long,
    val expenseCategory: String,
    val metadataId: Long,
    val hide: Boolean
)
