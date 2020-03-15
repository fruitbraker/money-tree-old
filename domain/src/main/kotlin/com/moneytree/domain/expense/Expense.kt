package com.moneytree.domain.expense

import java.math.BigDecimal
import java.sql.Timestamp
import java.time.OffsetDateTime

data class Expense (
    val expenseId: Long?,
    val transactionDate: OffsetDateTime,
    val transactionAmount: BigDecimal,
    val vendorId: Long,
    val vendorName: String,
    val expenseCategory: String,
    val metadataId: Long,
    val dateCreated: OffsetDateTime,
    val dateModified: OffsetDateTime,
    val notes: String?,
    val hide: Boolean
)
