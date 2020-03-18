package com.moneytree.domain.expense

import com.moneytree.domain.Result
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton
import javax.transaction.Transactional

@Singleton
@Transactional
class ExpenseService @Inject constructor(private val expenseRepository: IExpenseRepository): IExpenseService {

    override fun search(expenseId: Long): Result<ExpenseSummary, Exception> {
        return expenseRepository.search(expenseId)
    }

    override fun insert(expense: Expense): Result<Long, Exception> {
        return expenseRepository.insert(expense)
    }

}