package lotto.util;

import static lotto.util.LottoConstants.LOTTO_NUMBERS_COUNT;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface RandomLottoNumberGenerator {

    Integer generateNumber();
    default List<Integer> generateUniqueNumbers() {
        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() < LOTTO_NUMBERS_COUNT) {
            int number = generateNumber();
            numbers.add(number);
        }
        return numbers.stream().toList();
    }
}