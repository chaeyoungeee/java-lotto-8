package lotto.exception;

import static lotto.exception.ErrorCode.BLANK_INPUT;
import static lotto.exception.ErrorCode.NON_NUMERIC_INPUT;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class InputValidatorTest {
    @DisplayName("구매 금액 입력이 숫자가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", "one thousand", "!"})
    void 구매_금액이_숫자가_아니면_예외가_발생한다(String input) {
        assertThatThrownBy(() -> InputValidator.validatePurchaseAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NON_NUMERIC_INPUT.getMessage());
    }

    @DisplayName("당첨 번호 입력이 콤마로 구분된 숫자가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1,2,three,4,5,6",
            "1,2,!,4,5,6",
            "one,two,three,four,five,six",
            "1 2 3 4 5 6",
            "1.2.3.4.5.6",
            "1.2.3,4,5,6"
    })
    void 당첨_번호가_콤마로_구분된_숫자가_아니면_예외가_발생한다(String input) {
        assertThatThrownBy(() -> InputValidator.validateWinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NON_NUMERIC_INPUT.getMessage());
    }

    @DisplayName("입력이 공백이면 예외가 발생한다.")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "    "})
    void 입력이_공백이면_예외가_발생한다(String input) {
        assertThatThrownBy(() -> InputValidator.validateNotBlank(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(BLANK_INPUT.getMessage());
    }
}