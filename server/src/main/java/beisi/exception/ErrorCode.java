package beisi.exception;

/**
 * 错误码枚举类
 */
public enum ErrorCode {
    SUCCESS(0, "请求成功"),
    ERROR(1, "请求错误");

    private final int code;

    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
