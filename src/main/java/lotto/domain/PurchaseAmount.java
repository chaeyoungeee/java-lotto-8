package lotto.domain;

import static lotto.exception.InputErrorCode.INVALID_PURCHASE_AMOUNT;

public class PurchaseAmount {

    private final int value;

    private PurchaseAmount(int value) {
        validate(value);
        this.value = value;
    }

    public static PurchaseAmount of(int value) {
        return new PurchaseAmount(value);
    }

    private static void validate(int value) {
        validateMultipleOfThousand(value);
    }

    private static void validateMultipleOfThousand(int value) {
        if (value < 1000 || value % 1000 != 0) {
            throw new IllegalArgumentException(INVALID_PURCHASE_AMOUNT.getMessage());
        }
    }

    public int getValue() {
        return value;
    }

    public int getLottoCount() {
        return value / LOTTO_UNIT_PRICE;
    }
}
