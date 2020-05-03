import com.natpryce.hamkrest.assertion.assertThat
import org.http4k.core.Method.GET
import org.junit.jupiter.api.Test
import org.http4k.core.Request
import org.http4k.core.Status.Companion.OK
import org.http4k.hamkrest.hasStatus

class ExpenseCategoryRoutesTest : RoutesTestConfig() {

    @Test
    fun `OK status returned when retrieving list of expense category`() {
        assertThat(client(Request(GET, "$url/expense_category")), hasStatus(OK))
    }
}
