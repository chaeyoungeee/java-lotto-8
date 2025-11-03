package lotto.controller;

import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.util.LottoFactory;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinningCombination;
import lotto.domain.enums.LottoPrize;
import lotto.dto.LottoResultDto;
import lotto.dto.MatchCountDto;
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
        calculateResult(lottoService, lottos, winningCombination, purchaseAmount);
    }

    private List<Lotto> generateLottos(LottoService lottoService, PurchaseAmount purchaseAmount) {
        List<Lotto> lottos = lottoService.generateLottos(purchaseAmount);
        outputView.printPurchaseResult(purchaseAmount.getLottoCount(), toNumberList(lottos));
        return lottos;
    }

    private void calculateResult(LottoService lottoService, List<Lotto> lottos, WinningCombination winningCombination, PurchaseAmount purchaseAmount) {
        List<MatchCountDto> matchCounts = lottoService.evaluateLottos(lottos, winningCombination);
        LottoResultDto results = lottoService.getLottoResults(matchCounts);
        Map<LottoPrize, Integer> prizeCountMap = results.getPrizeCountMap();
        outputView.printResult(prizeCountMap);
        double returnRate = lottoService.calculateReturnRate(results, purchaseAmount);
        outputView.printReturnRate(returnRate);
    }

    private static List<List<Integer>> toNumberList(List<Lotto> lottos) {
        return lottos.stream()
                .map(Lotto::getNumbers)
                .toList();
    }
}