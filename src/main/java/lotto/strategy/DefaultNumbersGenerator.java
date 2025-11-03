package lotto.strategy;

import static lotto.util.LottoConstants.LOTTO_NUMBERS_COUNT;
import static lotto.util.LottoConstants.LOTTO_NUMBER_MAX;
import static lotto.util.LottoConstants.LOTTO_NUMBER_MIN;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class DefaultNumbersGenerator implements RandomNumbersGenerator {

    @Override
    public List<Integer> generateNumbers() {
        return Randoms.pickUniqueNumbersInRange(LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX, LOTTO_NUMBERS_COUNT);
    }
}
