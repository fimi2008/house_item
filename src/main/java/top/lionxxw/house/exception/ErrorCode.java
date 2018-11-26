package top.lionxxw.house.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 异常定义
 * <p>
 *
 * @author lionxxw
 * created on 2018/9/19 9:47
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {
    /**
     * 失败
     */
    ERROR(500, "系统异常")
    , MISS_PARAMETER(501, "缺少必要的参数:[#]")
    , INFO_NOT_EXIST(502, "[#]不存在或已删除")
    , DUPLICATE_DATA(503, "[#]信息已存在")
    , DATA_IS_NULL(504, "[#]为空")
    , FILE_IS_NULL(505, "文件为空")
    , FILE_NAME_TOO_LONG(506, "文件名称不得超过#字符")
    ;
    private int code;
    private String name;
}
