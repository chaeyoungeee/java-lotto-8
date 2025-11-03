package lotto.domain;

public class BonusNumber {

    private final int value;

    private BonusNumber(int value) {
        this.value = value;
    }

    public static BonusNumber of(int value) {
        return new BonusNumber(value);
    }
}
