package lotto.exception;


public enum InputErrorCode {
    BLANK_INPUT("[ERROR] 입력 값은 비어 있을 수 없습니다."),
    NON_NUMERIC_INPUT("[ERROR] 입력 값은 숫자여야 합니다."),

    private String message;

    InputErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
