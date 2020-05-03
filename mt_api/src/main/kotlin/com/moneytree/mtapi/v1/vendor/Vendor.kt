package com.moneytree.mtapi.v1.vendor

import com.moneytree.domain.vendor.Vendor as VendorDomain

data class Vendor(
    val vendor_id: Long? = null,
    val vendor_name: String
) {
    companion object {
        fun toDomain(vendor: Vendor): VendorDomain {
            return VendorDomain(
                vendorId = vendor.vendor_id,
                vendorName = vendor.vendor_name
            )
        }

        fun fromDomain(vendorDomain: VendorDomain): Vendor {
            return Vendor(
                vendor_id = vendorDomain.vendorId,
                vendor_name = vendorDomain.vendorName
            )
        }
    }
}
