package com.moneytree.mtapi.v1.expense_category

import com.moneytree.domain.expense_category.ExpenseCategory as ExpenseCategoryDomain

data class ExpenseCategory (
    val expense_category: String
) {
    companion object {
        fun toDomain(expenseCategory: ExpenseCategory): ExpenseCategoryDomain {
            return ExpenseCategoryDomain(
                expenseCategory = expenseCategory.expense_category
            )
        }

        fun fromDomain(expenseCategoryDomain: ExpenseCategoryDomain): ExpenseCategory {
            return ExpenseCategory(
                expense_category = expenseCategoryDomain.expenseCategory
            )
        }
    }
}