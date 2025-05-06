package com.rumi.util;

import com.rumi.file.FastDFSFile;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName: FastDFSUtil
 * @Description:
 * @Author: CSH
 * @Date: 2025-05-06 15:07
 */
public class FastDFSUtil {
    /**
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 15:10
     * @Description: 加载Tracker信息
     */
    static {
        //从classpath下获取文件对象获取路径
        try {
            String path = new ClassPathResource("fdfs_client.conf").getPath();
            ClientGlobal.init(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param file
     * @return java.lang.String[]
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 15:11
     * @Description: 文件上传
     */
    public static String[] upload(FastDFSFile file) throws Exception {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        //参数1 字节数组
        //参数2 扩展名(不带点)
        //参数3 元数据( 文件的大小,文件的作者,文件的创建时间戳)
        NameValuePair[] meta_list = new NameValuePair[]{new NameValuePair(file.getAuthor()), new NameValuePair(file.getName())};

        // strings[0]==group1  strings[1]=M00/00/00/wKjThF1aW9CAOUJGAAClQrJOYvs424.jpg
        return storageClient.upload_file(file.getContent(), file.getExt(), meta_list);
    }

    /**
     * @param groupName
     * @param remoteFileName
     * @return java.io.InputStream
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 17:33
     * @Description: 文件下载
     */
    public static InputStream downFile(String groupName, String remoteFileName) throws Exception {

        // 获取storageClient
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageClient storageClient = new StorageClient(trackerServer, null);

        // 根据组名 和 文件名 下载图片
        byte[] bytes = storageClient.download_file(groupName, remoteFileName);
        return new ByteArrayInputStream(bytes);

    }


    /**
     * @return java.lang.String
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 15:26
     * @Description: 获取tracker的ip和端口信息
     */
    public static String getTrackerUrl() throws IOException {
        // 1. 获取tracker对象
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        // 2. 获取tracker 的ip的信息
        String hostString = trackerServer.getInetSocketAddress().getHostString();
        // 3. 获取tracker 的端口的信息
        int g_tracker_http_port = ClientGlobal.getG_tracker_http_port();
        return "http://" + hostString + ":" + g_tracker_http_port;
    }


    /**
     * @param groupName
     * @param remoteFileName
     * @return org.csource.fastdfs.FileInfo
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 17:28
     * @Description: 根据文件名和组名获取文件的信息
     */
    public static FileInfo getFile(String groupName, String remoteFileName) throws IOException, MyException {
        // 获取Tracker再获取Storage
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        // 根据文件名和组名获取文件的信息
        return storageClient.get_file_info(groupName, remoteFileName);

    }

    public static void main(String[] args) throws Exception{
        InputStream group1 = downFile("group1", "M00/00/00/wKjIY2gZyZmABTNeAAFOx44nqyk48.jpeg");

        FileOutputStream os = new FileOutputStream("D:\\test.jpeg");

        byte[] buffer = new byte[1024];

        while(group1.read(buffer)!=-1){
            os.write(buffer);
        }
        os.flush();
        os.close();
        group1.close();

    }

}
