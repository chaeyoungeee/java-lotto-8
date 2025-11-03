package lotto.domain;

import static lotto.exception.InputErrorCode.DUPLICATE_BONUS_NUMBER;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import lotto.exception.InputErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningCombinationTest {
    @DisplayName("당첨 조합은 로또 번호 6개와 보너스 번호로 구성된다.")
    @Test
    void 당첨_조합은_로또_번호_6개와_보너스_번호로_구성된다() {
        Lotto winningLotto = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = BonusNumber.of(7);
        WinningCombination winningCombination = WinningCombination.of(winningLotto, bonusNumber);

        assertEquals(winningLotto, winningCombination.getWinningNumbers());
        assertEquals(bonusNumber, winningCombination.getBonusNumber());
    }

    @DisplayName("보너스 번호가 당첨 번호에 포함되면 예외가 발생한다.")
    @Test
    void 보너스_번호가_당첨_번호에_포함되면_예외가_발생한다() {
        Lotto winningLotto = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = BonusNumber.of(6);
        assertThatThrownBy(() -> WinningCombination.of(winningLotto, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(DUPLICATE_BONUS_NUMBER.getMessage());
    }
}