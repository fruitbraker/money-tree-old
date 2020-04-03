import com.moneytree.domain.Result
import com.moneytree.domain.expense.Expense
import com.moneytree.domain.expense.ExpenseSummary
import com.moneytree.persist.expense.ExpenseRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.lang.Exception
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.NoSuchElementException

class ExpenseRepositoryTest: PersistTestModule() {

    private val expenseRepository = injector.getInstance(ExpenseRepository::class.java)

    @Test
    fun `it returns NoSuchElementException with a nonexistent expense ID`() {
        val id = 9999999L
        val expected = NoSuchElementException("List is empty.")
        Expense(id, OffsetDateTime.now(), BigDecimal(1), 1, "TEST", 1, false)
        val result = when (val searchResult = expenseRepository.search(id)) {
            is Result.Ok -> searchResult.value
            is Result.Err -> searchResult.error.cause
        }
        assertEquals(result, expected.cause)
    }

    @Test
    fun `it successfully retrieves an Expense by id`() {
        val id = 1L
        val expected = ExpenseSummary(
            id,
            OffsetDateTime.parse("2020-04-02T18:24:55.931344-05:00"),
            BigDecimal(1.23),
            1,
            "VENDOR_TEST",
            "EXPENSE_CATEGORY_TEST",
            1,
            OffsetDateTime.parse("2020-04-02T18:24:55.931344-05:00"),
            OffsetDateTime.parse("2020-04-02T18:24:55.931344-05:00"),
            "TEST_NOTE",
            true
        )
        val result = when (val searchResult = expenseRepository.search(id)) {
            is Result.Ok -> searchResult.value
            is Result.Err -> searchResult.error
        }
        assertEquals(result, expected)
    }
}
