package calculator;

import java.util.List;

public class Calculator {
    private final List<BinaryOperation> operations;

    public Calculator(List<BinaryOperation> operations) {
        this.operations = operations;
    }

    public float calculate(String operator, float operand1, float operand2) {
        for(BinaryOperation operation : operations) {
            if(operation.matches(operator)) {
                return operation.execute(operand1, operand2);
            }
        }
        throw new IllegalArgumentException("No such operator: " + operator);
    }
}
