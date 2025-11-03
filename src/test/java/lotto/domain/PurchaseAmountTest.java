package lotto.domain;

import static lotto.exception.InputErrorCode.INVALID_PURCHASE_AMOUNT;
import static lotto.exception.InputErrorCode.NON_NUMERIC_INPUT;
import static lotto.util.LottoConstants.LOTTO_UNIT_PRICE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import lotto.exception.InputErrorCode;
import lotto.util.LottoConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PurchaseAmountTest {
    @DisplayName("구매 금액이 1,000원 단위가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {500, 1500, 250})
    void 구매_금액이_1000원_단위가_아니면_예외가_발생한다(int amount) {
        assertThatThrownBy(() -> PurchaseAmount.of(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_PURCHASE_AMOUNT.getMessage());
    }

    @DisplayName("구매 금액이 1,000원 이하면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {500, 400, 0, -1000})
    void 구매_금액이_1000원_이하면_예외가_발생한다(int amount) {
        assertThatThrownBy(() -> PurchaseAmount.of(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_PURCHASE_AMOUNT.getMessage());
    }

    @DisplayName("구매 금액만큼 로또 시도 횟수가 계산된다.")
    @ParameterizedTest
    @ValueSource(ints = {1000, 2000, 5000, 10000})
    void 구매_금액만큼_로또_시도_횟수가_계산된다(int amount) {
        PurchaseAmount purchaseAmount = PurchaseAmount.of(amount);
        assertEquals(amount / LOTTO_UNIT_PRICE, purchaseAmount.getLottoCount());
    };
}