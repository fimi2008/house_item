package top.lionxxw.house.exception;

import lombok.Getter;

/**
 * 系统异常
 * created on 2017/11/24 17:35
 *
 * @author lionxxw
 * @version 1.0.0
 */
public class ServerException extends RuntimeException {
    @Getter
    private int code;

    public ServerException(int code) {
        this.code = code;
    }

    public ServerException(int code, Throwable throwable) {
        super(throwable);
        this.code = code;
    }

    public ServerException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ServerException(int code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
    }

    public ServerException(int code, String message, String... arg) {
        super(replaceMessage(message, arg));
        this.code = code;
    }

    public ServerException(ErrorCode code, String... arg) {
        super(replaceMessage(code.getName(), arg));
        this.code = code.getCode();
    }

    public ServerException(ErrorCode code) {
        super(code.getName());
        this.code = code.getCode();
    }

    public static String replaceMessage(String message, String... arg) {
        for (String replaceStr : arg) {
            message = message.replaceFirst("#", replaceStr);
        }
        return message;
    }
}
