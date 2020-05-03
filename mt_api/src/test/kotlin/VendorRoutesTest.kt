import com.natpryce.hamkrest.assertion.assertThat
import org.http4k.core.Request
import org.junit.jupiter.api.Test
import org.http4k.core.Method.GET
import org.http4k.core.Status
import org.http4k.core.Status.Companion.NOT_FOUND
import org.http4k.core.Status.Companion.OK
import org.http4k.hamkrest.hasStatus

class VendorRoutesTest : RoutesTestConfig() {

    @Test
    fun `OK status returned when retrieving an existing vendor by id`() {
        val id = 1
        assertThat(client(Request(GET, "$url/vendor/$id")), hasStatus(OK))
    }

    @Test
    fun `NOT FOUND status returned when retrieving vendor with nonexistent id`() {
        val id = Long.MAX_VALUE
        assertThat(client(Request(GET, "$url/vendor/$id")), hasStatus(NOT_FOUND))
    }

    @Test
    fun `BAD REQUEST returned when retrieving vendor with garbage id`() {
        val id = "garbage123"
        assertThat(client(Request(GET, "$url/vendor/$id")), hasStatus(Status.BAD_REQUEST))
    }

    @Test
    fun `OK Status returned when retrieving list of vendors`() {
        assertThat(client(Request(GET, "$url/vendor")), hasStatus(OK))
    }
}
