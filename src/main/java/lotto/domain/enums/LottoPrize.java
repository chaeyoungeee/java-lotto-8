package lotto.domain.enums;

public enum LottoPrize {
    FIFTH(5000, 3, false),
    FOURTH(50000, 4, false),
    THIRD(1500000, 5, false),
    SECOND(30000000, 5, true),
    FIRST(2000000000, 6, false);

    private final int amount;
    private final int matchCount;
    private final boolean isBonusMatched;

    LottoPrize(int amount, int matchCount, boolean isBonusMatched) {
        this.amount = amount;
        this.matchCount = matchCount;
        this.isBonusMatched = isBonusMatched;
    }

    public int getAmount() {
        return amount;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isBonusMatch() {
        return isBonusMatched;
    }

    public static LottoPrize of(int matchCount, boolean isBonusMatched) {
        for (LottoPrize prize : values()) {
            if (prize.matchCount == matchCount && prize.isBonusMatched == isBonusMatched) {
                return prize;
            }
        }
        return null;
    }
}