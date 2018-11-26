package top.lionxxw.house.web.base;

import lombok.Data;
import top.lionxxw.house.exception.ErrorCode;

/**
 * 接口返回结果

 * created on 2018/9/16 16:00
 *
 * @author lionxxw
 * @version 1.0.0
 */
@Data
public class ServerResult {
    /**
     * 是否成功
     */
    private boolean success = true;
    /**
     * 错误码
     */
    private int errorCode = 0;
    /**
     * 错误信息
     */
    private String errorMsg = "";
    /**
     * 数据
     */
    private Object data;

    private ServerResult() {
    }

    /**
     * 成功
     * @param data
     * @return
     */
    public static ServerResult ok(Object data) {
        ServerResult serverResult = new ServerResult();
        serverResult.setData(data);
        return serverResult;
    }

    /**
     * 成功
     * @return
     */
    public static ServerResult ok() {
        return ok(null);
    }

    /**
     * 失败
     * @param errorCode
     * @param errorMsg
     * @return
     */
    public static ServerResult error(int errorCode, String errorMsg){
        ServerResult serverResult = new ServerResult();
        serverResult.setSuccess(false);
        serverResult.setErrorCode(errorCode);
        serverResult.setErrorMsg(errorMsg);
        return serverResult;
    }

    public static ServerResult error(ErrorCode errorCode){
        return error(errorCode.getCode(), errorCode.getName());
    }
}
