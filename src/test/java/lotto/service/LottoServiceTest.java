package lotto.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinningCombination;
import lotto.domain.enums.LottoPrize;
import lotto.dto.LottoResultDto;
import lotto.strategy.RandomNumbersGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoServiceTest {

    private RandomNumbersGenerator customRandomNumbersGenerator;
    private LottoService lottoService;
    List<Lotto> lottos;
    WinningCombination winningCombination;

    @BeforeEach
    public void setUp() {
        customRandomNumbersGenerator = () -> List.of(1, 2, 3, 4, 5, 6);
        lottoService = new LottoService(customRandomNumbersGenerator);
        lottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)), //1등
                new Lotto(List.of(1, 2, 3, 4, 5, 7)), //2등
                new Lotto(List.of(1, 2, 3, 4, 5, 8)), //3등
                new Lotto(List.of(1, 2, 3, 4, 8, 9)), //4등
                new Lotto(List.of(1, 2, 3, 8, 9, 10)), //5등
                new Lotto(List.of(10, 11, 12, 13, 14, 15)) //순위X
        );
        winningCombination = WinningCombination.of(
                Lotto.from(List.of(1, 2, 3, 4, 5, 6)),
                BonusNumber.of(7)
        );
    }

    @DisplayName("구매 금액만큼 로또를 만들어낸다.")
    @Test
    void 구매_금액만큼_로또를_만들어낸다() {
        PurchaseAmount purchaseAmount = PurchaseAmount.of(5000);
        int lottoCount = purchaseAmount.getLottoCount();
        List<Lotto> lottos = lottoService.generateLottos(purchaseAmount);
        assertEquals(lottoCount, lottos.size());
    }

    @DisplayName("로또 결과를 계산하고 총 상금을 검증한다.")
    @Test
    void 로또_결과를_계산하고_총_상금을_검증한다() {
        LottoResultDto result = lottoService.calculateLottoResults(lottos, winningCombination);
        int totalPrize = Arrays.stream(LottoPrize.values())
                .mapToInt(LottoPrize::getAmount)
                .sum();

        assertAll(
            () -> assertEquals(1, result.getPrizeCountMap().get(LottoPrize.FIRST)),
            () -> assertEquals(1, result.getPrizeCountMap().get(LottoPrize.SECOND)),
            () -> assertEquals(1, result.getPrizeCountMap().get(LottoPrize.THIRD)),
            () -> assertEquals(1, result.getPrizeCountMap().get(LottoPrize.FOURTH)),
            () -> assertEquals(1, result.getPrizeCountMap().get(LottoPrize.FIFTH)),
            () -> assertEquals(totalPrize, result.getTotalAmount())
        );
    }

    @DisplayName("수익률을 계산한다.")
    @Test
    void 수익률을_계산한다() {
        PurchaseAmount purchaseAmount = PurchaseAmount.of(10000);

        LottoResultDto result = lottoService.calculateLottoResults(lottos, winningCombination);
        double returnRate = lottoService.calculateReturnRate(result, purchaseAmount);
        int totalWinnings = LottoPrize.FIRST.getAmount() +
                LottoPrize.SECOND.getAmount() +
                LottoPrize.THIRD.getAmount() +
                LottoPrize.FOURTH.getAmount() +
                LottoPrize.FIFTH.getAmount();
        double expectedReturnRate = ((double) totalWinnings / purchaseAmount.getValue()) * 100;

        assertEquals(expectedReturnRate, returnRate);
    }
}