package lotto.exception;


public enum InputErrorCode {
    BLANK_INPUT("[ERROR] 입력 값은 비어 있을 수 없습니다."),
    NON_NUMERIC_INPUT("[ERROR] 입력 값은 숫자여야 합니다."),
    INVALID_PURCHASE_AMOUNT("[ERROR] 구매 금액은 1,000원 이상이며, 1,000원 단위로 입력해야 합니다."),
    INVALID_LOTTO_NUMBER("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    DUPLICATE_LOTTO_NUMBER("[ERROR] 로또 번호는 중복될 수 없습니다."),
    DUPLICATE_BONUS_NUMBER("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다."),
    INVALID_LOTTO_NUMBERS_COUNT("[ERROR] 로또 번호는 6개여야 합니다.");


    private String message;

    InputErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
