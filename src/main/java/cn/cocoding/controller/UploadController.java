package cn.cocoding.controller;

import cn.cocoding.config.lang.Result;
import cn.cocoding.entity.UploadFileResultData;
import cn.cocoding.service.impl.UploadService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * UploadController
 *
 * @author huzi
 * @date 2021/3/31
 */
@RestController
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private UploadService uploadService;
    @PostMapping("/image")
    public Result uploadImage(@RequestParam("file") MultipartFile file){
        System.out.println("访问到这");
        UploadFileResultData uploadFileResultData = uploadService.upload(file);
        System.out.println("访问到这1");
        if (StringUtils.isBlank(uploadFileResultData.getFileUrl())) {
            return Result.fail("上传失败");
        }
        return Result.succ(uploadFileResultData.toString());
    }
}
