package lotto.exception;

import static lotto.exception.InputErrorCode.BLANK_INPUT;
import static lotto.exception.InputErrorCode.NON_NUMERIC_INPUT;
import static lotto.util.InputConstants.WINNING_NUMBERS_DELIMITER;

public class InputValidator {

    public static void validatePurchaseAmount(String input) {
        validateNotBlank(input);
        validateNumber(input);
    }

    public static void validateWinningNumbers(String input) {
        validateNotBlank(input);
        validateSeperatedNumericToken(input);
    }

    public static void validateSeperatedNumericToken(String input) {
        String[] numbers = input.split(WINNING_NUMBERS_DELIMITER);
        for (String number : numbers) {
            validateNumber(number);
        }
    }

    public static void validateBonusNumber(String input) {
        validateNotBlank(input);
        validateNumber(input);
    }

    public static void validateNotBlank(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(BLANK_INPUT.getMessage());
        }
    }

    public static void validateNumber(String value) {
        if (!value.matches("\\d+")) {
            throw new IllegalArgumentException(NON_NUMERIC_INPUT.getMessage());
        }
    }
}