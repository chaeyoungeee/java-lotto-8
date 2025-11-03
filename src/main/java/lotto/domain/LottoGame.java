package lotto.domain;

public class LottoGame {

    private final PurchaseAmount purchaseAmount;
    private final WinningCombination winningCombination;

    public LottoGame(PurchaseAmount purchaseAmount, WinningCombination winningCombination) {
        this.purchaseAmount = purchaseAmount;
        this.winningCombination = winningCombination;
    }

    public PurchaseAmount getPurchaseAmount() {
        return purchaseAmount;
    }

    public WinningCombination getWinningCombination() {
        return winningCombination;
    }
}
