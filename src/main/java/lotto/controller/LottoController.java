package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
import lotto.util.LottoFactory;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinningCombination;
import lotto.dto.LottoResultDto;
import lotto.service.LottoService;
import lotto.strategy.RandomNumbersGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final RandomNumbersGenerator randomNumbersGenerator;

    public LottoController(InputView inputView, OutputView outputView, RandomNumbersGenerator randomNumbersGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.randomNumbersGenerator = randomNumbersGenerator;
    }

    public void run() {
        LottoService lottoService = new LottoService(randomNumbersGenerator);

        PurchaseAmount purchaseAmount = LottoFactory.createPurchaseAmount(inputView, outputView);
        List<Lotto> lottos = generateLottos(lottoService, purchaseAmount);
        WinningCombination winningCombination = LottoFactory.createWinningCombination(inputView, outputView);
        LottoResultDto results = calculateResult(lottoService, lottos, winningCombination, purchaseAmount);
        calculateReturnRate(lottoService, purchaseAmount, results);
    }

    private List<Lotto> generateLottos(LottoService lottoService, PurchaseAmount purchaseAmount) {
        List<Lotto> lottos = lottoService.generateLottos(purchaseAmount);
        outputView.printPurchaseResult(purchaseAmount.getLottoCount(), toNumberList(lottos));
        return lottos;
    }

    private LottoResultDto calculateResult(LottoService lottoService, List<Lotto> lottos, WinningCombination winningCombination, PurchaseAmount purchaseAmount) {
        LottoResultDto results = lottoService.calculateLottoResults(lottos, winningCombination);
        outputView.printResult(results.getPrizeCountMap());
        return results;
    }

    private void calculateReturnRate(LottoService lottoService, PurchaseAmount purchaseAmount, LottoResultDto results) {
        double returnRate = lottoService.calculateReturnRate(results, purchaseAmount);
        outputView.printReturnRate(returnRate);
    }

    private static List<List<Integer>> toNumberList(List<Lotto> lottos) {
        return lottos.stream()
                .map(Lotto::getNumbers)
                .toList();
    }
}