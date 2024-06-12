import org.springframework.expression.spel.standard.SpelExpressionParser
import org.springframework.expression.spel.support.StandardEvaluationContext
import kotlin.reflect.jvm.javaMethod

object SpelVararg {
    fun evaluateExpression(expression: String): Any {
        val parser = SpelExpressionParser()
        val exp = parser.parseExpression(expression)
        val context = StandardEvaluationContext().apply {
            registerFunction("formatAny", SpelVararg::formatAny.javaMethod!!)
            registerFunction("formatString", SpelVararg::formatString.javaMethod!!)
        }
        return exp.getValue(context)
    }

    @JvmStatic
    fun formatAny(format: String, vararg args: Any): String {
        return format.format(*args)
    }

    @JvmStatic
    fun formatString(format: String, vararg args: String): String {
        return format.format(*args)
    }
}