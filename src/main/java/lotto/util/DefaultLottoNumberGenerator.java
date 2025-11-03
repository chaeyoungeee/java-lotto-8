package lotto.util;

import static lotto.util.LottoConstants.LOTTO_NUMBER_MAX;
import static lotto.util.LottoConstants.LOTTO_NUMBER_MIN;

import camp.nextstep.edu.missionutils.Randoms;

public class DefaultLottoNumberGenerator implements RandomLottoNumberGenerator {

    @Override
    public Integer generateNumber() {
        return Randoms.pickNumberInRange(LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX);
    }
}
