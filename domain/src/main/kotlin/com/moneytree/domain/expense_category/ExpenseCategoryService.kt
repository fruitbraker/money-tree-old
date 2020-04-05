package com.moneytree.domain.expense_category

import com.moneytree.domain.Result
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton
import javax.transaction.Transactional

@Singleton
@Transactional
class ExpenseCategoryService @Inject constructor(
    private val expenseCategoryRepository: IExpenseCategoryRepository
) : IExpenseCategoryService {
    override fun get(): Result<List<ExpenseCategory>, Exception> {
        return expenseCategoryRepository.get()
    }
}