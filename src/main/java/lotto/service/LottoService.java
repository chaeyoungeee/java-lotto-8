package lotto.service;

import static lotto.util.LottoConstants.LOTTO_UNIT_PRICE;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.PurchaseAmount;
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
}