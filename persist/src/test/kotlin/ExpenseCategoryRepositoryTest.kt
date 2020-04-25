import com.moneytree.domain.Result
import com.moneytree.domain.otherwise
import com.moneytree.persist.expense_category.ExpenseCategoryRepository
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.number.OrderingComparison.greaterThan
import org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ExpenseCategoryRepositoryTest: PersistTestModule() {

    private val expenseCategoryRepository = injector.getInstance(ExpenseCategoryRepository::class.java)

    @Test
    fun `it successfully retrieves all expense categories`() {
        val result = when (val getResult = expenseCategoryRepository.get()) {
            is Result.Ok -> getResult.value.size
            is Result.Err -> -1
        }
        assertThat(result, greaterThanOrEqualTo(0))
    }

    @Test
    fun `it successfully inserts a new expense category`() {
        val newExpenseCategory = "TEST_INSERT_DELETE"
        val expectedLength = when (val result = expenseCategoryRepository.get()) {
            is Result.Ok -> result.value.size + 1
            is Result.Err -> -9
        }
        assert(expectedLength > 0)
        val resultInsert = expenseCategoryRepository.insert(newExpenseCategory)
        assert(resultInsert is Result.Ok<*>)
        val resultLength = when (val result = expenseCategoryRepository.get()) {
            is Result.Ok -> {
                assertEquals(result.value.last().expenseCategory, newExpenseCategory)
                result.value.size
            }
            is Result.Err -> -1
        }
        assertEquals(resultLength, expectedLength)
    }

    @Test
    fun `it successfully deletes an existing expense category`() {
        val toBeDeleted = "TEST_INSERT_DELETE"
        val expectedLength = when (val result = expenseCategoryRepository.get()) {
            is Result.Ok -> result.value.size - 1
            is Result.Err -> -9
        }
        assert(expectedLength >= 0)
        val resultInsert = expenseCategoryRepository.delete(toBeDeleted)
        assert(resultInsert is Result.Ok<*>)
        val resultLength = expenseCategoryRepository.get().otherwise { emptyList() }.size
        assertEquals(resultLength, expectedLength)
    }
}
