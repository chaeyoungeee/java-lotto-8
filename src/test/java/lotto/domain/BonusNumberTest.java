package lotto.domain;

import static lotto.exception.InputErrorCode.INVALID_LOTTO_NUMBER;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BonusNumberTest {
    @DisplayName("보너스 로또 번호가 1~45 범위를 벗어나면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void 보너스_로또_번호가_1_45_범위를_벗어나면_예외가_발생한다(int number) {
        assertThatThrownBy(() -> BonusNumber.of(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_LOTTO_NUMBER.getMessage());
    }
}