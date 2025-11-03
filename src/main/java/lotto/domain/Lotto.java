package lotto.domain;

import static lotto.exception.InputErrorCode.INVALID_LOTTO_NUMBER;

import java.util.List;
import lotto.exception.InputErrorCode;

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
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER.getMessage());
        }
    }

    // TODO: 추가 기능 구현
}