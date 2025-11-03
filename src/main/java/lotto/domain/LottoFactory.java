package lotto.domain;

import java.util.List;
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

    public static Lotto createWinningNumbers(InputView inputView, OutputView outputView) {
        while (true) {
            try {
                List<Integer> numbers = inputView.inputWinningNumbers();
                return Lotto.from(numbers);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    public static BonusNumber createBonusNumber(InputView inputView, OutputView outputView) {
        while (true) {
            try {
                int value = inputView.inputBonusNumber();
                return BonusNumber.of(value);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
