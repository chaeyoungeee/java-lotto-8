package lotto.domain;

import static lotto.exception.InputErrorCode.INVALID_LOTTO_NUMBER;
import static lotto.util.LottoConstants.LOTTO_NUMBER_MAX;
import static lotto.util.LottoConstants.LOTTO_NUMBER_MIN;

public class BonusNumber {

    private final Integer value;

    private BonusNumber(Integer value) {
        validate(value);
        this.value = value;
    }

    public static BonusNumber of(Integer value) {
        return new BonusNumber(value);
    }

    public static void validate(Integer value) {
        validateNumberRange(value);
    }

    private static void validateNumberRange(Integer value) {
        if (value < LOTTO_NUMBER_MIN || value > LOTTO_NUMBER_MAX) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER.getMessage());
        }
    }

    public Integer getValue() {
        return value;
    }
}