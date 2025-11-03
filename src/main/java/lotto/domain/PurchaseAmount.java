package lotto.domain;

public class PurchaseAmount {

    private final int value;

    private PurchaseAmount(int value) {
        this.value = value;
    }

    public static PurchaseAmount of(int value) {
        return new PurchaseAmount(value);
    }
}
