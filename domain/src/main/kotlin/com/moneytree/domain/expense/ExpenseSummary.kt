package com.moneytree.domain.expense

import java.math.BigDecimal
import java.sql.Timestamp
import java.time.OffsetDateTime

data class ExpenseSummary (
    val expenseId: Long?,
    val transactionDate: OffsetDateTime,
    val transactionAmount: Double,
    val vendorId: Long,
    val vendorName: String,
    val expenseCategory: String,
    val metadataId: Long,
    val dateCreated: OffsetDateTime,
    val dateModified: OffsetDateTime,
    val notes: String?,
    val hide: Boolean
)
