import com.moneytree.domain.Result
import com.moneytree.domain.vendor.Vendor
import com.moneytree.persist.vendor.VendorRepository
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.greaterThanOrEqualTo
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class VendorRepositoryTest : PersistTestModule() {

    private val vendorRepository = injector.getInstance(VendorRepository::class.java)

    @Test
    fun `it successfully retrieves vendor by id`() {
        val id = 1L
        val expectedVendor = Vendor(1L, "VENDOR_TEST")
        val resultVendor = when (val result = vendorRepository.search(id)) {
            is Result.Ok -> result.value
            is Result.Err -> result.error
        }
        assertEquals(resultVendor, expectedVendor)
    }

    @Test
    fun `it successfully retrieves all vendors`() {
        val result = when (val getResult = vendorRepository.getAll()) {
            is Result.Ok -> getResult.value.size
            is Result.Err -> -1
        }
        assertThat(result, greaterThanOrEqualTo(0))
    }

    @Test
    fun `it successfully inserts a new vendor`() {
        val expectedVendorName = "TEST_VENDOR_INSERT_DELETE"
        val expectedLength = when (val result = vendorRepository.getAll()) {
            is Result.Ok -> result.value.size + 1
            is Result.Err -> -9
        }
        assert(expectedLength > 0)
        val resultInsert = vendorRepository.insert(Vendor(vendorName = expectedVendorName))
        assert(resultInsert is Result.Ok<*>)
        val resultLength = when (val result = vendorRepository.getAll()) {
            is Result.Ok -> {
                assertEquals(result.value.last().vendorName, expectedVendorName)
                result.value.size
            }
            is Result.Err -> -9
        }
        assertEquals(resultLength, expectedLength)
    }

    @Test
    fun `it successfully deletes a vendor by id`() {
        var deleteId = -1L
        val expectedLength = when (val result = vendorRepository.getAll()) {
            is Result.Ok -> {
                deleteId = result.value.last().vendorId!!
                result.value.size - 1
            }
            is Result.Err -> -9
        }
        assert(expectedLength >= 0)
        val resultInsert = vendorRepository.delete(deleteId)
        assert(resultInsert is Result.Ok<*>)
        val resultLength = when (val result = vendorRepository.getAll()) {
            is Result.Ok -> {
                result.value.size
            }
            is Result.Err -> -9
        }
        assertEquals(resultLength, expectedLength)
    }
}
