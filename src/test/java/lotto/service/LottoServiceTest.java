package lotto.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.PurchaseAmount;
import lotto.strategy.RandomNumbersGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoServiceTest {

    private RandomNumbersGenerator customRandomNumbersGenerator;
    private LottoService lottoService;

    @BeforeEach
    public void setUp() {
        customRandomNumbersGenerator = () -> List.of(1, 2, 3, 4, 5, 6);
        lottoService = new LottoService(customRandomNumbersGenerator);
    }

    @Test
    @DisplayName("구매 금액만큼 로또를 만들어낸다.")
    void 구매_금액만큼_로또를_만들어낸다() {
        PurchaseAmount purchaseAmount = PurchaseAmount.of(5000);
        int lottoCount = purchaseAmount.getLottoCount();
        List<Lotto> lottos = lottoService.generateLottos(purchaseAmount);
        assertEquals(lottoCount, lottos.size());
    }
}