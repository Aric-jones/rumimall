package com.rumi.util;

import com.rumi.file.FastDFSFile;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName: FastDFSUtil
 * @Description: FastDFS工具类
 * @Author: CSH
 * @Date: 2025-05-06 15:07
 */
public class FastDFSUtil {
    /**
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 15:10
     * @Description: 加载Tracker连接信息信息
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
        // 获取trackerServer
        TrackerServer trackerServer = getTrackerServer();
        // 获取storageClient
        StorageClient storageClient = getStorageClient(trackerServer);
        //参数1 字节数组
        //参数2 扩展名(不带点)
        //参数3 元数据( 文件的大小,文件的作者,文件的创建时间戳)
        NameValuePair[] meta_list = new NameValuePair[]{new NameValuePair(file.getAuthor()), new NameValuePair(file.getName())};

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
        // 获取trackerServer
        TrackerServer trackerServer = getTrackerServer();
        // 获取storageClient
        StorageClient storageClient = getStorageClient(trackerServer);

        // 根据组名 和 文件名 下载图片
        byte[] bytes = storageClient.download_file(groupName, remoteFileName);
        return new ByteArrayInputStream(bytes);

    }


    /**
     * @param groupName
     * @param remoteFileName
     * @return void
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 17:33
     * @Description: 文件删除
     */
    public static void deleteFile(String groupName, String remoteFileName) throws Exception {
        // 获取trackerServer
        TrackerServer trackerServer = getTrackerServer();
        // 获取storageClient
        StorageClient storageClient = getStorageClient(trackerServer);
        int i = storageClient.delete_file(groupName, remoteFileName);
        if (i == 0) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }

    }


    /**
     * @return java.lang.String
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 15:26
     * @Description: 获取tracker的ip和端口信息
     */
    public static String getTrackerUrl() throws IOException {
        // 1. 获取trackerServer
        TrackerServer trackerServer = getTrackerServer();
        // 2. 获取tracker 的ip的信息
        String hostString = trackerServer.getInetSocketAddress().getHostString();
        // 3. 获取tracker 的端口的信息
        int trackerHttpPort = ClientGlobal.getG_tracker_http_port();
        return "http://" + hostString + ":" + trackerHttpPort;
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
        // 获取trackerServer
        TrackerServer trackerServer = getTrackerServer();
        // 获取storageClient
        StorageClient storageClient = getStorageClient(trackerServer);
        // 根据文件名和组名获取文件的信息
        return storageClient.get_file_info(groupName, remoteFileName);

    }


    /**
     * @param groupName
     * @return org.csource.fastdfs.StorageServer
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 20:01
     * @Description: 获取Storages信息
     */
    public static StorageServer getStorages(String groupName) throws Exception {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerClient.getStoreStorage(trackerServer, groupName);
    }


    /**
     * @param groupName
     * @param remoteFileName
     * @return org.csource.fastdfs.ServerInfo[]
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 20:06
     * @Description: 获取Storage的ip和端口信息
     */
    public static ServerInfo[] getServerInfo(String groupName, String remoteFileName) throws Exception {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerClient.getFetchStorages(trackerServer, groupName, remoteFileName);

    }

    private static StorageClient getStorageClient(TrackerServer trackerServer) {
        return new StorageClient(trackerServer, null);
    }

    private static TrackerServer getTrackerServer() throws IOException {
        // 创建一个tracker客户端
        TrackerClient trackerClient = new TrackerClient();
        // 通过tracker客户端获取tracker服务信息
        return trackerClient.getConnection();
    }


}
