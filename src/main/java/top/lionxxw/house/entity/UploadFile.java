package top.lionxxw.house.entity;

import lombok.Data;

/**
 * 文件上传
 * <p>
 *
 * @author lionxxw
 * created on 2018/9/20 9:30
 */
@Data
public class UploadFile extends Entity<Long>{
    private String name;
    private byte[] file;
}
