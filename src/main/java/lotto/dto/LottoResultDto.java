package lotto.dto;

import static java.util.stream.Collectors.*;
import static lotto.domain.enums.LottoPrize.NONE;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import lotto.domain.enums.LottoPrize;

public class LottoResultDto {

    private List<LottoPrize> results;

    public LottoResultDto(List<MatchCountDto> matchCounts) {
        results = matchCounts.stream()
            .map(MatchCountDto::toLottoPrize)
            .filter(prize -> prize != NONE)
            .toList();
    }

    public List<LottoPrize> getResults() {
        return results;
    }

    public Map<LottoPrize, Integer> getPrizeCountMap() {
        return results.stream()
            .collect(
                groupingBy(Function.identity(), summingInt(prize -> 1))
            );
    }
}