package com.rumi.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rumi.goods.pojo.Album;

import java.util.List;

/**
 * @Author:CSH
 * @Updator:CSH
 * @Date 2025/5/6 21:02
 * @Description:
 */
public interface IAlbumService extends IService<Album> {

    /**
     * @param album
     * @param page
     * @param size
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.rumi.pojo.Album>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 21:02
     * @Description: Album分页条件搜索实现
     */
    IPage<Album> findPage(Album album, int page, int size);

    /**
     * @param page
     * @param size
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.rumi.pojo.Album>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 21:02
     * @Description: Album分页搜索实现
     */
    IPage<Album> findPage(int page, int size);

    /**
     * @param album
     * @return java.util.List<com.rumi.pojo.Album>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 21:08
     * @Description: 多条件搜索
     */
    List<Album> findList(Album album);

}
