package lotto.service;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinningCombination;
import lotto.dto.LottoResultDto;
import lotto.dto.MatchCountDto;
import lotto.strategy.RandomNumbersGenerator;

public class LottoService {

    private final RandomNumbersGenerator randomNumbersGenerator;

    public LottoService(RandomNumbersGenerator randomNumbersGenerator) {
        this.randomNumbersGenerator = randomNumbersGenerator;
    }

    public List<Lotto> generateLottos(PurchaseAmount purchaseAmount) {
        List<Lotto> lottos = new ArrayList<>();
        int lottoCount = purchaseAmount.getLottoCount();
        while (lottos.size() < lottoCount) {
            lottos.add(Lotto.from(new ArrayList<>(randomNumbersGenerator.generateNumbers())));
        }
        return lottos;
    }

    public List<MatchCountDto> evaluateLottos(List<Lotto> lottos, WinningCombination winningCombination) {
        return lottos.stream()
                .map(lotto -> lotto.getResult(winningCombination))
                .toList();
    }

    public LottoResultDto getLottoResults(List<MatchCountDto> matchCounts) {
        return new LottoResultDto(matchCounts);
    }

    public double calculateReturnRate(LottoResultDto results, PurchaseAmount purchaseAmount) {
        return ((double) results.getTotalAmount() / purchaseAmount.getValue()) * 100;
    }
}