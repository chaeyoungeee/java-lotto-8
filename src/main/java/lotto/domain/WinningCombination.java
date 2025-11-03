package lotto.domain;

import lotto.exception.InputErrorCode;

public class WinningCombination {

    private final Lotto winningNumbers;
    private final BonusNumber bonusNumber;

    private WinningCombination(Lotto winningNumbers, BonusNumber bonusNumber) {
        validate(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public static WinningCombination of(Lotto winningNumbers, BonusNumber bonusNumber) {
        return new WinningCombination(winningNumbers, bonusNumber);
    }

    public static void validate(Lotto winningNumbers, BonusNumber bonusNumber) {
        validateNumberDuplication(winningNumbers, bonusNumber);
    }

    private static void validateNumberDuplication(Lotto lotto, BonusNumber bonusNumber) {
        if (lotto.contains(bonusNumber.getValue())) {
            throw new IllegalArgumentException(InputErrorCode.DUPLICATE_BONUS_NUMBER.getMessage());
        }
    }
}