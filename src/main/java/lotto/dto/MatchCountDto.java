package lotto.dto;

import static lotto.domain.enums.LottoPrize.NONE;

import lotto.domain.enums.LottoPrize;

public class MatchCountDto {

    private final int matchCount;
    private final boolean isBonusMatched;

    public MatchCountDto(int matchCount, boolean isBonusMatched) {
        this.matchCount = matchCount;
        this.isBonusMatched = isBonusMatched;
    }

    public LottoPrize toLottoPrize() {
        for (LottoPrize lottoPrize : LottoPrize.values()) {
            if (this.matchCount == lottoPrize.getMatchCount() &&
                this.isBonusMatched == lottoPrize.isBonusMatch()) {
                return lottoPrize;
            }
        }
        return NONE;
    }
}
