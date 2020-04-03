import com.moneytree.mtapi.v1.setUpServer
import com.natpryce.hamkrest.assertion.assertThat
import org.http4k.client.OkHttp
import org.http4k.core.Method.GET
import org.junit.jupiter.api.Test
import org.http4k.core.Request
import org.http4k.core.Status
import org.http4k.core.Status.Companion.OK
import org.http4k.hamkrest.hasStatus
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

class ExpenseRoutesTest {

    private val client = OkHttp()
    private val server = setUpServer()
    private val url = "http://localhost:9000"

    @BeforeEach
    fun setup() {
        server.start()
    }

    @AfterEach
    fun teardown() {
        server.stop()
    }

    @Test
    fun `health check happy path`() {
        assertThat(client(Request(GET, "${url}/health")), hasStatus(OK))
    }

    @Test
    fun `OK status returned when retrieving an existing expense by id`() {
        val id = 1
        assertThat(client(Request(GET, "${url}/expense/${id}")), hasStatus(OK))
    }

    @Test
    fun `NO CONTENT status returned when retrieving expense with nonexistent id`() {
        val id = Long.MAX_VALUE
        assertThat(client(Request(GET, "${url}/expense/${id}")), hasStatus(Status.NOT_FOUND))
    }

    @Test
    fun `BAD REQUEST status returned when retrieving expense with garbage id`() {
        val id = "garbage123"
        assertThat(client(Request(GET, "${url}/expense/${id}")), hasStatus(Status.BAD_REQUEST))
    }
}
