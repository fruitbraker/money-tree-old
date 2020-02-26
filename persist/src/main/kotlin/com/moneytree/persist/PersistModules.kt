package com.moneytree.persist

import com.google.inject.AbstractModule
import com.google.inject.Inject
import com.google.inject.Provides
import com.moneytree.domain.expense.IExpenseRepository
import com.moneytree.persist.expense.ExpenseRepository
import com.zaxxer.hikari.HikariDataSource
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import javax.sql.DataSource

class PersistModules: AbstractModule() {
    override fun configure() {
        bind(IExpenseRepository::class.java).to(ExpenseRepository::class.java).asEagerSingleton()
    }


    @Provides
    fun dbConnection(): HikariDataSource {
        val ds = HikariDataSource()
        ds.jdbcUrl = "jdbc:postgresql://localhost:5432/moneytree"
        ds.username = "postgres"
        ds.password = "qwertyuiop"
        return ds
    }

    @Provides
    @Inject
    fun dslContext(ds: HikariDataSource): DSLContext {
        return DSL.using(ds, SQLDialect.POSTGRES)
    }

}