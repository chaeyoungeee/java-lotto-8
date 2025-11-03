package lotto.domain;

public class LottoGame {

    private final PurchaseAmount purchaseAmount;
    private final Lotto winningNumbers;
    private final BonusNumber bonusNumber;

    public LottoGame(PurchaseAmount purchaseAmount, Lotto winningNumbers, BonusNumber bonusNumber) {
        this.purchaseAmount = purchaseAmount;
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public PurchaseAmount getPurchaseAmount() {
        return purchaseAmount;
    }

    public Lotto getWinningNumbers() {
        return winningNumbers;
    }

    public BonusNumber getBonusNumber() {
        return bonusNumber;
    }
}
