package com.moneytree.domain.vendor

import com.moneytree.domain.Result
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton
import javax.transaction.Transactional

@Singleton
@Transactional
class VendorService @Inject constructor(
    private val vendorRepository: IVendorRepository
) : IVendorService {
    override fun search(vendorId: Long): Result<Vendor, Exception> {
        return vendorRepository.search(vendorId)
    }

    override fun getAll(): Result<List<Vendor>, Exception> {
        return vendorRepository.getAll()
    }

    override fun insert(vendor: Vendor): Result<Unit, Exception> {
        return vendorRepository.insert(vendor)
    }

    override fun delete(vendorId: Long): Result<Unit, Exception> {
        return vendorRepository.delete(vendorId)
    }
}
