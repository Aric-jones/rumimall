package com.rumi.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: FastDFSFile
 * @Description:
 * @Author: CSH
 * @Date: 2025-05-06 15:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FastDFSFile {

    //文件名字
    private String name;
    //文件内容
    private byte[] content;
    //文件扩展名
    private String ext;
    //文件MD5摘要值
    private String md5;
    //文件创建作者
    private String author;


    public FastDFSFile(String name, byte[] content, String ext) {
        this.name = name;
        this.content = content;
        this.ext = ext;
    }

}
