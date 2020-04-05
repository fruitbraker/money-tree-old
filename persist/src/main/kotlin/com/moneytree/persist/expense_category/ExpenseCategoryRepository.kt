package com.moneytree.persist.expense_category

import com.moneytree.domain.Result
import com.moneytree.domain.expense_category.ExpenseCategory
import com.moneytree.domain.expense_category.IExpenseCategoryRepository
import org.jooq.DSLContext
import java.lang.Exception
import javax.inject.Inject

class ExpenseCategoryRepository @Inject constructor(
    private val dslContext: DSLContext
): IExpenseCategoryRepository {
    override fun get(): Result<List<ExpenseCategory>, Exception> {
        TODO("Not yet implemented")
    }
}
