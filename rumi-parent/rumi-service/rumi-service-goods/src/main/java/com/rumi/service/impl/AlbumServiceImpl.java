package com.rumi.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rumi.dao.AlbumMapper;
import com.rumi.pojo.Album;
import com.rumi.service.IAlbumService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Author:CSH
 * @Updator:CSH
 * @Date 2025/5/6 21:02
 * @Description:
 */
@Service
public class AlbumServiceImpl extends ServiceImpl<AlbumMapper, Album> implements IAlbumService {

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
    @Override
    public IPage<Album> findPage(Album album, int page, int size) {
        LambdaQueryWrapper<Album> wrapper = new LambdaQueryWrapper<Album>().like(Objects.nonNull(album.getTitle()), Album::getTitle, album.getTitle());
        Page<Album> albumPage = new Page<Album>(page, size);
        return this.page(albumPage, wrapper);
    }

    /**
     * @param page
     * @param size
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.rumi.pojo.Album>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 21:02
     * @Description: Album分页搜索实现
     */
    @Override
    public IPage<Album> findPage(int page, int size) {
        Page<Album> albumPage = new Page<Album>(page, size);
        return this.page(albumPage);
    }

    /**
     * @param album
     * @return java.util.List<com.rumi.pojo.Album>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 21:08
     * @Description: 多条件搜索
     */
    @Override
    public List<Album> findList(Album album) {
        LambdaQueryWrapper<Album> wrapper = new LambdaQueryWrapper<Album>().like(Objects.nonNull(album.getTitle()), Album::getTitle, album.getTitle());
        return this.list(wrapper);
    }

}
