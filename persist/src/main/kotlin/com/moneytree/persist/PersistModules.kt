package com.moneytree.persist

import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.moneytree.domain.expense.IExpenseRepository
import com.moneytree.persist.db.generated.tables.daos.ExpenseDao
import com.moneytree.persist.expense.ExpenseRepository
import com.zaxxer.hikari.HikariDataSource
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.conf.MappedSchema
import org.jooq.conf.RenderMapping
import org.jooq.conf.Settings
import org.jooq.impl.DSL
import javax.inject.Inject
import javax.inject.Singleton

class PersistModules: AbstractModule() {
    override fun configure() {
        bind(IExpenseRepository::class.java).to(ExpenseRepository::class.java).asEagerSingleton()
    }

    private val schema = "mtdev"

    @Provides
    @Singleton
    fun dbConnection(): HikariDataSource {
        val ds = HikariDataSource()
        ds.jdbcUrl = "jdbc:postgresql://localhost:5432/moneytree?currentSchema=${schema}"
        ds.username = "postgres"
        ds.password = "qwertyuiop"
        return ds
    }

    @Provides
    @Singleton
    @Inject
    fun dslContext(ds: HikariDataSource): DSLContext {
        val settings = Settings().withRenderMapping(
            RenderMapping().withSchemata(
                MappedSchema().withInput("mtdev").withOutput(schema)
            )
        ).withExecuteLogging(true)
        return DSL.using(ds, SQLDialect.POSTGRES, settings)
    }

}