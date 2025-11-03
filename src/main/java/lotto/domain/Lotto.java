package lotto.domain;

import static lotto.exception.InputErrorCode.INVALID_LOTTO_NUMBER;
import static lotto.exception.InputErrorCode.INVALID_LOTTO_NUMBERS_COUNT;
import static lotto.util.LottoConstants.LOTTO_NUMBERS_COUNT;
import static lotto.util.LottoConstants.LOTTO_NUMBER_MAX;
import static lotto.util.LottoConstants.LOTTO_NUMBER_MIN;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import lotto.domain.enums.LottoPrize;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public static Lotto from(List<Integer> numbers) {
        return new Lotto(numbers);
    }

    private void validate(List<Integer> numbers) {
        validateNumbersCount(numbers);
        validateNumberRange(numbers);
        validateNumberDuplication(numbers);
    }

    private static void validateNumbersCount(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBERS_COUNT) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBERS_COUNT.getMessage());
        }
    }

    private static void validateNumberRange(List<Integer> numbers) {
        for (Integer number : numbers) {
            if (number < LOTTO_NUMBER_MIN || number > LOTTO_NUMBER_MAX) {
                throw new IllegalArgumentException(INVALID_LOTTO_NUMBER.getMessage());
            }
        }
    }

    private static void validateNumberDuplication(List<Integer> numbers) {
        long distinctCount = numbers.stream().distinct().count();
        if (distinctCount != LOTTO_NUMBERS_COUNT) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER.getMessage());
        }
    }

    public boolean contains(Integer number) {
        return numbers.contains(number);
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    public int countMatchingNumber(Lotto winningNumbers) {
        HashSet<Integer> numbersSet = new HashSet<>(numbers);
        HashSet<Integer> winningNumbersSet = new HashSet<>(winningNumbers.getNumbers());
        numbersSet.retainAll(winningNumbersSet);
        return numbersSet.size();
    }

    public boolean isBonusMatched(BonusNumber bonusNumber) {
        return contains(bonusNumber.getValue());
    }

    public LottoPrize getPrize(WinningCombination winningCombination) {
        int matchCount = countMatchingNumber(winningCombination.getWinningNumbers());
        boolean isBonusMatched = isBonusMatched(winningCombination.getBonusNumber());
        return LottoPrize.of(matchCount, isBonusMatched);
    }
}