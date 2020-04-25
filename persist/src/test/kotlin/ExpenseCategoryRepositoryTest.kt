import com.moneytree.domain.Result
import com.moneytree.domain.otherwise
import com.moneytree.persist.expense_category.ExpenseCategoryRepository
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.number.OrderingComparison.greaterThan
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ExpenseCategoryRepositoryTest: PersistTestModule() {

    private val expenseCategoryRepository = injector.getInstance(ExpenseCategoryRepository::class.java)

    @Test
    fun `it successfully retrieves all expense categories`() {
        val firstElementExpected = "EXPENSE_CATEGORY_TEST"
        val result = expenseCategoryRepository.get().otherwise { emptyList() }
        assertThat(result.size, greaterThan(0))
        assertEquals(result[0].expenseCategory, firstElementExpected)
    }

    @Test
    fun `it successfully inserts a new expense category`() {
        val newExpenseCategory = "TEST_NEW"
        val expected = Unit
        val result = expenseCategoryRepository.insert(newExpenseCategory)
        assert(result is Result.Ok<*>)
    }

    @Test
    fun `it successfully deletes an existing expense category`() {

    }
}