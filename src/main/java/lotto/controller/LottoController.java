package lotto.controller;

import lotto.domain.LottoFactory;
import lotto.domain.LottoGame;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void start() {
        LottoGame lottoGame = new LottoGame(
                LottoFactory.createPurchaseAmount(inputView, outputView),
                LottoFactory.createWinningNumbers(inputView, outputView),
                LottoFactory.createBonusNumber(inputView, outputView)
        );
    }
}