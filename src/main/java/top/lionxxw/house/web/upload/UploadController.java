package top.lionxxw.house.web.upload;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.lionxxw.house.entity.UploadFile;
import top.lionxxw.house.exception.ErrorCode;
import top.lionxxw.house.exception.ServerException;
import top.lionxxw.house.service.BillService;
import top.lionxxw.house.web.base.ServerResult;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 文件上传中心
 * <p>
 *
 * @author lionxxw
 * created on 2018/9/20 9:27
 */
@Controller
@RequestMapping("upload")
@Slf4j
public class UploadController {

    @Autowired
    private BillService billService;

    private final static int FILE_NAME_MAX_LENGTH = 30;

    @GetMapping("list")
    public String list(Model model) {
        List<UploadFile> files = billService.findFiles();
        model.addAttribute("files", files);
        return "upload/list";
    }

    @PostMapping
    @ResponseBody
    public ServerResult upload(@RequestParam("file") MultipartFile file) {
        uploadFile(file);
        return ServerResult.ok();
    }

    @PostMapping("billImg")
    @ResponseBody
    public ServerResult uploadBillImg(@RequestParam("file") MultipartFile file, Long billId, Long brandId) {
        Long fileId = uploadFile(file);
        billService.updateFile(fileId, billId, brandId);
        return ServerResult.ok();
    }


    @GetMapping("show")
    public void showImage(HttpServletResponse response, Long id) {
        UploadFile file = billService.getFileById(id);
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            out.write(file.getFile());
        } catch (IOException e) {
            log.error("getOutputStream 获取异常：e：{}", e);
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    log.error("close 流异常：e：{}", e);
                }
            }
        }
    }

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    private Long uploadFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new ServerException(ErrorCode.FILE_IS_NULL);
        }
        String name = file.getOriginalFilename();
        if (StringUtils.length(name) > FILE_NAME_MAX_LENGTH) {
            throw new ServerException(ErrorCode.FILE_NAME_TOO_LONG, String.valueOf(FILE_NAME_MAX_LENGTH));
        }
        int size = (int) file.getSize();
        log.info("上传文件名称：{} 文件大小：{}", name, size);
        UploadFile uploadFile = new UploadFile();
        uploadFile.setName(name);
        try {
            byte[] bytes = file.getBytes();
            uploadFile.setFile(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        billService.saveFile(uploadFile);
        return uploadFile.getId();
    }
}
