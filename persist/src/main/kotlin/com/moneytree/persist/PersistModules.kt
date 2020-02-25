package com.moneytree.persist

import com.google.inject.AbstractModule
import com.moneytree.domain.expense.IExpenseRepository
import com.moneytree.persist.expense.ExpenseRepository
import javax.sql.DataSource

class PersistModules: AbstractModule() {
    override fun configure() {
        bind(IExpenseRepository::class.java).to(ExpenseRepository::class.java).asEagerSingleton()
    }


}