package com.rumi.controller;

import com.rumi.common.entity.Result;
import com.rumi.common.entity.StatusCode;
import com.rumi.file.FastDFSFile;
import com.rumi.util.FastDFSUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: FileUploadController
 * @Description:
 * @Author: CSH
 * @Date: 2025-05-06 15:17
 */
@RestController
@RequestMapping("/upload")
public class FileUploadController {


    /**
     * @param file
     * @return com.rumi.common.entity.Result<java.lang.String>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 16:27
     * @Description: 文件上传
     */
    @PostMapping
    public Result<String> upload(@RequestParam("file") MultipartFile file) throws Exception {
        //1. 创建图片文件对象(封装)
        //2. 调用工具类实现图片上传

        //String substring = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

        FastDFSFile fastdfsfile = new FastDFSFile(
                //原来的文件名  1234.jpg
                file.getOriginalFilename(),
                //文件本身的字节数组
                file.getBytes(),
                StringUtils.getFilenameExtension(file.getOriginalFilename())
        );
        String[] upload = FastDFSUtil.upload(fastdfsfile);

        //  upload[0] group1
        //  upload[1] M00/00/00/wKjThF1aW9CAOUJGAAClQrJOYvs424.jpg
        //3. 拼接图片的全路径返回

        // http://192.168.211.132:8080/group1/M00/00/00/wKjThF1aW9CAOUJGAAClQrJOYvs424.jpg

        // http://192.168.211.132:8080  +
        return new Result<String>(true, StatusCode.OK, "上传成功", FastDFSUtil.getTrackerUrl() + "/" + upload[0] + "/" + upload[1]);
    }
}
