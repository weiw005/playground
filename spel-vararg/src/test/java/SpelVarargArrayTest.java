import org.junit.jupiter.api.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.ReflectionUtils;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;

public class SpelVarargArrayTest {
    @Test
    public void arrayFlattenedAsVararg_Before_6_1_10() {
        assertThat(evaluateExpression("#objectVarargDemo({'World', 123})"))
                .isEqualTo("There are 2 argument(s): World 123");
    }

    @Test
    public void arrayFlattenedAsVararg_After_6_1_11() {
        assertThat(evaluateExpression("#objectVarargDemo({'World', 123})"))
                .isEqualTo("There are 1 argument(s): [World, 123]");
    }

    private static final Method demoMethod =
            ReflectionUtils.findMethod(SpelVarargArrayTest.class, "objectVarargDemo", Object[].class);

    private static Object evaluateExpression(String expression) {
        SpelExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression(expression);
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.registerFunction("objectVarargDemo", demoMethod);
        return exp.getValue(context);
    }

    static String objectVarargDemo(Object... args) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("There are %d argument(s): ", args.length));
        for (Object arg : args) {
            sb.append(arg).append(" ");
        }
        return sb.toString().trim();
    }
}
