package com.rumi.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rumi.common.entity.Result;
import com.rumi.common.entity.StatusCode;
import com.rumi.goods.pojo.Album;
import com.rumi.service.IAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author:CSH
 * @Updator:CSH
 * @Date 2025/5/6 21:20
 * @Description: Album管理控制层
 */
@RestController
@RequestMapping("/album")
@CrossOrigin
public class AlbumController {
    @Autowired
    private IAlbumService albumService;

    /**
     * @param album
     * @param page
     * @param size
     * @return com.rumi.common.entity.Result<com.baomidou.mybatisplus.core.metadata.IPage < com.rumi.pojo.Album>>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 21:02
     * @Description: Album分页条件搜索实现
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result<IPage<Album>> findPage(@RequestBody(required = false) Album album, @PathVariable int page, @PathVariable int size) {
        IPage<Album> pageInfo = albumService.findPage(album, page, size);
        return new Result<IPage<Album>>(true, StatusCode.OK, "查询成功", pageInfo);
    }


    /**
     * @param page
     * @param size
     * @return com.rumi.common.entity.Result<com.baomidou.mybatisplus.core.metadata.IPage < com.rumi.pojo.Album>>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 21:07
     * @Description: Album分页搜索实现
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<IPage<Album>> findPage(@PathVariable int page, @PathVariable int size) {
        IPage<Album> pageInfo = albumService.findPage(page, size);
        return new Result<IPage<Album>>(true, StatusCode.OK, "查询成功", pageInfo);
    }


    /**
     * @param album
     * @return com.rumi.common.entity.Result<java.util.List < com.rumi.pojo.Album>>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 21:08
     * @Description: 多条件搜索
     */
    @PostMapping(value = "/search")
    public Result<List<Album>> findList(@RequestBody(required = false) Album album) {
        List<Album> list = albumService.findList(album);
        return new Result<List<Album>>(true, StatusCode.OK, "查询成功", list);
    }


    /**
     * @param id
     * @return com.rumi.common.entity.Result
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 21:10
     * @Description: 根据id删除
     */
    @DeleteMapping(value = "/{id}")
    public Result<String> delete(@PathVariable Long id) {
        boolean remove = albumService.removeById(id);
        if (!remove) {
            return new Result<String>(false, StatusCode.ERROR, "删除失败");
        }
        return new Result<String>(true, StatusCode.OK, "删除成功");
    }


    /**
     * @param album
     * @param id
     * @return com.rumi.common.entity.Result
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 21:14
     * @Description: 修改
     */
    @PutMapping(value = "/{id}")
    public Result<String> update(@RequestBody Album album, @PathVariable Long id) {
        album.setId(id);
        boolean update = albumService.updateById(album);
        if (!update) {
            return new Result<String>(false, StatusCode.ERROR, "修改失败");
        }
        return new Result<String>(true, StatusCode.OK, "修改成功");
    }


    /**
     * @param album
     * @return com.rumi.common.entity.Result
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 21:16
     * @Description: 添加
     */
    @PostMapping
    public Result<String> add(@RequestBody Album album) {
        boolean save = albumService.save(album);
        if (!save) {
            return new Result<String>(false, StatusCode.ERROR, "添加失败");
        }
        return new Result<String>(true, StatusCode.OK, "添加成功");
    }


    /**
     * @param id
     * @return com.rumi.common.entity.Result<com.rumi.pojo.Album>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 21:18
     * @Description: 根据id查询
     */
    @GetMapping("/{id}")
    public Result<Album> findById(@PathVariable Long id) {
        Album album = albumService.getById(id);
        return new Result<Album>(true, StatusCode.OK, "查询成功", album);
    }


    /**
     * @return com.rumi.common.entity.Result<com.rumi.pojo.Album>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 21:18
     * @Description: 查询Album全部信息
     */
    @GetMapping
    public Result<List<Album>> findAll() {
        List<Album> list = albumService.list();
        return new Result<List<Album>>(true, StatusCode.OK, "查询成功", list);
    }
}
