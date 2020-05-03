import com.moneytree.mtapi.v1.setUpServer
import org.http4k.client.OkHttp
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

open class RoutesTestConfig {
    protected val client = OkHttp()
    private val server = setUpServer("test")
    protected val url = "http://localhost:9000"

    @BeforeEach
    fun setup() {
        server.start()
    }

    @AfterEach
    fun teardown() {
        server.stop()
    }
}
