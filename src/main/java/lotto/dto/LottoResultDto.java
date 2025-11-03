package lotto.dto;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lotto.domain.enums.LottoPrize;

public class LottoResultDto {

    private final EnumMap<LottoPrize, Integer> prizeCountMap = new EnumMap<>(LottoPrize.class);

    public LottoResultDto(List<LottoPrize> prizes) {
        Arrays.stream(LottoPrize.values()).forEach(prize -> prizeCountMap.put(prize, 0));
        prizes.forEach(prize -> prizeCountMap.put(prize, prizeCountMap.get(prize) + 1));
    }

    public int getTotalAmount() {
        return prizeCountMap.entrySet().stream()
                .mapToInt(e -> e.getKey().getAmount() * e.getValue())
                .sum();
    }

    public Map<LottoPrize, Integer> getPrizeCountMap() {
        return Collections.unmodifiableMap(prizeCountMap);
    }
}