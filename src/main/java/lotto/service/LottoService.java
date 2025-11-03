package lotto.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lotto.domain.Lotto;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinningCombination;
import lotto.domain.enums.LottoPrize;
import lotto.dto.LottoResultDto;
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

    public LottoResultDto calculateLottoResults(List<Lotto> lottos, WinningCombination winningCombination) {
        List<LottoPrize> prizes = getLottoPrizes(lottos, winningCombination);
        return new LottoResultDto(prizes);
    }

    private List<LottoPrize> getLottoPrizes(List<Lotto> lottos, WinningCombination winningCombination) {
        return lottos.stream()
                .map(lotto -> {
                    return lotto.getPrize(winningCombination);
                })
                .filter(Objects::nonNull)
                .toList();
    }

    public double calculateReturnRate(LottoResultDto results, PurchaseAmount purchaseAmount) {
        return ((double) results.getTotalAmount() / purchaseAmount.getValue()) * 100;
    }
}