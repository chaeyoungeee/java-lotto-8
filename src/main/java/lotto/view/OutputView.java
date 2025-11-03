package lotto.view;

import java.util.List;
import java.util.Map;
import lotto.domain.enums.LottoPrize;

public class OutputView {

    private final String PURCHASE_RESULT_MESSAGE = "%d개를 구매했습니다.";
    private final String RESULT_MESSAGE = "당첨 통계\n---";
    private final String RESULT_MATCH_COUNT_MESSAGE = "%d개 일치";
    private final String RESULT_BONUS_MATCH_MESSAGE = ", 보너스 볼 일치";
    private final String RESULT_AMOUNT_MESSAGE = " (%,d원) - %d개\n";
    private final String RETURN_RATE_MESSAGE = "총 수익률은 %.1f%%입니다.";


    public void printPurchaseResult(int count, List<List<Integer>> lottos) {
        System.out.printf(PURCHASE_RESULT_MESSAGE + "\n", count);
        for (List<Integer> lotto : lottos) {
            System.out.println(lotto.stream().sorted().toList());
        }
    }

    public void printResult(Map<LottoPrize, Integer> results) {
        System.out.println(RESULT_MESSAGE);
        results.forEach((prize, count) -> {
                System.out.printf(RESULT_MATCH_COUNT_MESSAGE, prize.getMatchCount());
                if (prize.isBonusMatch()) {
                    System.out.print(RESULT_BONUS_MATCH_MESSAGE);
                }
                System.out.printf(RESULT_AMOUNT_MESSAGE, prize.getAmount(), count);
            });
    }

    public void printReturnRate(double rate) {
        System.out.printf(RETURN_RATE_MESSAGE, rate);
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}