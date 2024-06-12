import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class SpelVarargTest {
    @Test
    fun formatStringEmpty() {
        assertEquals("Hello ", SpelVararg.evaluateExpression("#formatString('Hello %s', '')"))
    }

    @Test
    fun formatAnyEmpty() {
        assertEquals("Hello ", SpelVararg.evaluateExpression("#formatAny('Hello %s', '')"))
    }

    @Test
    fun formatStringSingleArgumentWithComma() {
        assertEquals("Hello a, b", SpelVararg.evaluateExpression("#formatString('Hello %s', 'a, b')"))
    }

    @Test
    fun formatAnySingleArgumentWithComma() {
        assertEquals("Hello a, b", SpelVararg.evaluateExpression("#formatAny('Hello %s', 'a, b')"))
    }

    @Test
    fun formatStringMultiArgumentWithComma() {
        assertEquals("Hello a, b; c, d", SpelVararg.evaluateExpression("#formatString('Hello %s; %s', 'a, b', 'c, d')"))
    }

    @Test
    fun formatAnyMultiArgumentWithComma() {
        assertEquals("Hello a, b; c, d", SpelVararg.evaluateExpression("#formatAny('Hello %s; %s', 'a, b', 'c, d')"))
    }
}