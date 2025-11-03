package lotto.domain;

import java.util.List;

public class WinningNumbers {

    private final List<Integer> numbers;

    private WinningNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public static WinningNumbers of(List<Integer> numbers) {
        return new WinningNumbers(numbers);
    }
}
