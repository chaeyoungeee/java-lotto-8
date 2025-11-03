package lotto;

import lotto.controller.LottoController;
import lotto.util.DefaultLottoNumberGenerator;
import lotto.util.RandomLottoNumberGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        RandomLottoNumberGenerator randomLottoNumberGenerator = new DefaultLottoNumberGenerator();

        LottoController lottoController = new LottoController(inputView, outputView, randomLottoNumberGenerator);
        lottoController.run();
    }
}