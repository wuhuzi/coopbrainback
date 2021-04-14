package cn.cocoding.service.impl;

import cn.cocoding.entity.UploadFileResultData;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.lang3.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class UploadService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadService.class);
//    private static final List<String> CONTENT_TYPES = Arrays.asList("image/jpeg", "image/gif","image/png");
    @Autowired
    private FastFileStorageClient storageClient;
    public UploadFileResultData upload(MultipartFile file) {
        //获取文件名称
        String originalFilename = file.getOriginalFilename();
        System.out.println("originalFilename:" + originalFilename);
        //校检文件的类型
//        String contentType = file.getContentType();
//        if (!CONTENT_TYPES.contains(contentType)){
//            LOGGER.info("文件类型不合法:{}",originalFilename);
//            return null;
//        }
        try {
            // 校验文件的内容
//            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
//            if (bufferedImage == null){
//                LOGGER.info("文件内容不合法：{}", originalFilename);
//                return null;
//            }
            // 保存到服务器
            //file.transferTo(new File("D:\\upload\\images\\" + originalFilename));
            String ext = StringUtils.substringAfterLast(originalFilename, ".");
            System.out.println("ext: " + ext);
            StorePath storePath = this.storageClient.uploadFile(file.getInputStream(), file.getSize(), ext, null);
            System.out.println(" " + storePath);
            // 生成url地址，将文件名和地址返回
            UploadFileResultData uploadFileResultData = new UploadFileResultData()
                    .setFileName(originalFilename)
                    .setFileUrl("http://172.31.72.122:8888/" + storePath.getFullPath());
            System.out.println(uploadFileResultData.toString());
            return uploadFileResultData;
        } catch (IOException e) {
            LOGGER.info("服务器内部错误：{}", originalFilename);
            e.printStackTrace();
        }
        return null;
    }
}