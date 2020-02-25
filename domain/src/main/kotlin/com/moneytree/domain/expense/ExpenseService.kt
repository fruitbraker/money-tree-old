package com.moneytree.domain.expense

import javax.inject.Inject
import javax.inject.Singleton
import javax.transaction.Transactional

@Singleton
@Transactional
class ExpenseService @Inject constructor(private val expenseRepository: IExpenseRepository): IExpenseService {
    override fun search(expenseId: Long) {
        expenseRepository.search(expenseId)
    }
}