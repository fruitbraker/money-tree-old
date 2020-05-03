package com.moneytree.persist.vendor

import com.moneytree.domain.Result
import com.moneytree.domain.toErr
import com.moneytree.domain.toOk
import com.moneytree.domain.vendor.IVendorRepository
import com.moneytree.domain.vendor.Vendor
import com.moneytree.persist.db.generated.Tables.VENDOR
import org.jooq.DSLContext
import org.jooq.Record
import java.lang.Exception
import javax.inject.Inject

class VendorRepository @Inject constructor(
    private val dslContext: DSLContext
) : IVendorRepository {

    private fun Record.toDomain(): Vendor {
        return Vendor(
            vendorId = this[VENDOR.VENDOR_ID],
            vendorName = this[VENDOR.VENDOR_NAME]
        )
    }

    override fun search(vendorId: Long): Result<Vendor, Exception> {
        val result = dslContext.configuration().dsl()
            .select(VENDOR.asterisk())
            .from(VENDOR)
            .where(VENDOR.VENDOR_ID.eq(vendorId))
            .fetch()

        return try {
            result.mapNotNull { it.toDomain() }.first().toOk()
        } catch (e: Exception) {
            return e.toErr()
        }
    }

    override fun getAll(): Result<List<Vendor>, Exception> {
        val result = dslContext.configuration().dsl()
            .select(VENDOR.asterisk())
            .from(VENDOR)
            .limit(500)
            .fetch()

        return try {
            result.mapNotNull { it.toDomain() }.toOk()
        } catch (e: Exception) {
            e.toErr()
        }
    }

    override fun insert(vendor: Vendor): Result<Unit, Exception> {
        return try {
            dslContext.configuration().dsl()
                .insertInto(VENDOR)
                .columns(VENDOR.VENDOR_NAME)
                .values(vendor.vendorName)
                .execute()
            Unit.toOk()
        } catch (e: Exception) {
            e.toErr()
        }
    }

    override fun delete(vendorId: Long): Result<Unit, Exception> {
        return try {
            dslContext.configuration().dsl()
                .deleteFrom(VENDOR)
                .where(VENDOR.VENDOR_ID.eq(vendorId))
                .execute()
            Unit.toOk()
        } catch (e: Exception) {
            e.toErr()
        }
    }
}
