package com.moneytree.domain.vendor

import com.moneytree.domain.Result
import java.lang.Exception

interface IVendorRepository {
    fun search(vendorId: Long): Result<Vendor, Exception>
    fun getAll(): Result<List<Vendor>, Exception>
    fun insert(vendor: Vendor): Result<Unit, Exception>
    fun delete(vendorId: Long): Result<Unit, Exception>
}
