package com.moneytree.persist.expense_category

import com.moneytree.domain.Result
import com.moneytree.domain.expense_category.ExpenseCategory
import com.moneytree.domain.expense_category.IExpenseCategoryRepository
import com.moneytree.domain.toErr
import com.moneytree.domain.toOk
import com.moneytree.persist.db.generated.Tables.*
import org.jooq.DSLContext
import org.jooq.Record
import java.lang.Exception
import javax.inject.Inject

class ExpenseCategoryRepository @Inject constructor(
    private val dslContext: DSLContext
) : IExpenseCategoryRepository {

    private fun Record.toDomain(): ExpenseCategory {
        return ExpenseCategory(
            expenseCategory = this[EXPENSE_CATEGORY.EXPENSE_CATEGORY_ID]
        )
    }

    override fun get(): Result<List<ExpenseCategory>, Exception> {
        val result = dslContext.configuration().dsl()
            .select(EXPENSE_CATEGORY.asterisk())
            .from(EXPENSE_CATEGORY)
            .fetch()

        return try {
            result.mapNotNull { it.toDomain() }.toOk()
        } catch (e: Exception) {
            e.toErr()
        }
    }

    override fun insert(expenseCategory: String): Result<Unit, Exception> {
        return try {
            dslContext.configuration().dsl()
                .insertInto(EXPENSE_CATEGORY)
                .columns(EXPENSE_CATEGORY.EXPENSE_CATEGORY_ID)
                .values(expenseCategory)
                .execute()
            Unit.toOk()
        } catch (e: Exception) {
            e.toErr()
        }
    }

    override fun delete(expenseCategory: String): Result<Unit, Exception> {
        return try {
            dslContext.configuration().dsl()
                .deleteFrom(EXPENSE_CATEGORY)
                .where(EXPENSE_CATEGORY.EXPENSE_CATEGORY_ID.eq(expenseCategory))
                .execute()
            Unit.toOk()
        } catch (e: Exception) {
            e.toErr()
        }
    }
}
