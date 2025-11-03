package lotto.service;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinningCombination;
import lotto.domain.enums.LottoPrize;
import lotto.dto.LottoResultDto;
import lotto.dto.MatchCountDto;
import lotto.util.RandomLottoNumberGenerator;

public class LottoService {

    private final RandomLottoNumberGenerator randomLottoNumberGenerator;

    public LottoService(RandomLottoNumberGenerator randomLottoNumberGenerator) {
        this.randomLottoNumberGenerator = randomLottoNumberGenerator;
    }

    public List<Lotto> generateLottos(PurchaseAmount purchaseAmount) {
        List<Lotto> lottos = new ArrayList<>();
        int lottoCount = purchaseAmount.getLottoCount();
        while (lottos.size() < lottoCount) {
            List<Integer> lotto = randomLottoNumberGenerator.generateUniqueNumbers();
            lottos.add(Lotto.from(lotto));
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
        return (double) results.getTotalAmount() / purchaseAmount.getValue();
    }
}