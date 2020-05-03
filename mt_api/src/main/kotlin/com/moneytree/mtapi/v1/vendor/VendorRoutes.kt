package com.moneytree.mtapi.v1.vendor

import com.moneytree.domain.Result
import com.moneytree.domain.vendor.IVendorService
import org.http4k.core.*
import org.http4k.core.Method.DELETE
import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.format.Gson.auto
import org.http4k.lens.Path
import org.http4k.lens.long
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import org.http4k.routing.routes
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VendorRoutes @Inject constructor(
    private val vendorService: IVendorService
) {
    private val vendorLens = Body.auto<Vendor>().toLens()
    private val vendorListLens = Body.auto<List<Vendor>>().toLens()
    private val vendorIdLens = Path.long().of("vendor_id")

    fun vendorRoutes(): RoutingHttpHandler {
        return routes(
            "/vendor" bind GET to {
                try {
                    when (val result = vendorService.getAll()) {
                        is Result.Ok -> {
                            Response(Status.OK).with(
                                vendorListLens of result.value.map { Vendor.fromDomain(it) }
                            )
                        }
                        is Result.Err -> {
                            Response(Status.BAD_REQUEST).body("Bad input data.")
                        }
                    }
                } catch (e: Exception) {
                    Response(Status.BAD_REQUEST).body("Bad input data.")
                }
            },

            "/vendor" bind POST to {
                try {
                    when (vendorService.insert(Vendor.toDomain(vendorLens(it)))) {
                        is Result.Ok -> Response(Status.CREATED)
                        is Result.Err -> Response(Status.BAD_REQUEST).body("Bad input data.")
                    }
                } catch (e: Exception) {
                    Response(Status.BAD_REQUEST).body("Bad input data.")
                }
            },

            "/vendor/{vendor_id}" bind GET to {
                try {
                    val vendorId = vendorIdLens(it)
                    when (val result = vendorService.search(vendorId)) {
                        is Result.Ok -> Response(Status.OK).with(vendorLens of Vendor.fromDomain(result.value))
                        is Result.Err -> {
                            when (result.error) {
                                is java.util.NoSuchElementException -> Response(Status.NOT_FOUND).body("Vendor not found.")
                                else -> Response(Status.BAD_REQUEST)
                            }
                        }
                    }
                } catch (e: Exception) {
                    Response(Status.BAD_REQUEST).body("Bad input data.")
                }
            },

            "/vendor/{vendor_id}" bind DELETE to {
                try {
                    val vendorId = vendorIdLens(it)
                    when (vendorService.delete(vendorId)) {
                        is Result.Ok -> Response(Status.NO_CONTENT)
                        is Result.Err -> Response(Status.NOT_FOUND)
                    }
                } catch (e: Exception) {
                    Response(Status.BAD_REQUEST).body("Bad input data.")
                }
            }
        )
    }
}
