package lotto.util;

import java.util.List;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinningCombination;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoFactory {

    public static PurchaseAmount createPurchaseAmount(InputView inputView, OutputView outputView) {
        while (true) {
            try {
                int value = inputView.inputPurchaseAmount();
                return PurchaseAmount.of(value);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    public static WinningCombination createWinningCombination(InputView inputView, OutputView outputView) {
        Lotto winningNumbers = getWinningNumbers(inputView, outputView);
        return getWinningCombination(inputView, outputView, winningNumbers);
    }

    private static Lotto getWinningNumbers(InputView inputView, OutputView outputView) {
        while (true) {
            try {
                List<Integer> numbers = inputView.inputWinningNumbers();
                return Lotto.from(numbers);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private static WinningCombination getWinningCombination(InputView inputView, OutputView outputView, Lotto winningNumbers) {
        while (true) {
            try {
                Integer value = inputView.inputBonusNumber();
                BonusNumber bonusNumber = BonusNumber.of(value);
                return WinningCombination.of(winningNumbers, bonusNumber);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}