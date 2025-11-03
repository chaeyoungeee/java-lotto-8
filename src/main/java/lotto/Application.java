package lotto;

import lotto.controller.LottoController;
import lotto.strategy.DefaultNumbersGenerator;
import lotto.strategy.RandomNumbersGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        RandomNumbersGenerator randomNumbersGenerator = new DefaultNumbersGenerator();

        LottoController lottoController = new LottoController(inputView, outputView, randomNumbersGenerator);
        lottoController.run();
    }
}