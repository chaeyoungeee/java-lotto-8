package lotto.domain;

import static lotto.exception.InputErrorCode.INVALID_LOTTO_NUMBER;
import static lotto.util.LottoConstants.LOTTO_NUMBERS_COUNT;
import static lotto.util.LottoConstants.LOTTO_NUMBER_MIN;

public class BonusNumber {

    private final Integer value;

    private BonusNumber(Integer value) {
        this.value = value;
    }

    public static BonusNumber of(Integer value) {
        return new BonusNumber(value);
    }

    private static void validateNumberRange(Integer value) {
        if (value < LOTTO_NUMBER_MIN || value > LOTTO_NUMBERS_COUNT) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER.getMessage());
        }
    }

    public Integer getValue() {
        return value;
    }
}
