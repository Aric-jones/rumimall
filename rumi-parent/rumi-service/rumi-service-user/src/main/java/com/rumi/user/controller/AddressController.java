package com.rumi.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rumi.common.entity.Result;
import com.rumi.common.entity.StatusCode;
import com.rumi.user.pojo.Address;
import com.rumi.user.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author:CSH
 * @Updator:CSH
 * @Date 2025/5/17 22:38
 * @Description: Address管理控制层
 */
@RestController
@RequestMapping("/address")
@CrossOrigin
public class AddressController {
    @Autowired
    private IAddressService addressService;

    /**
     * @param address
     * @param page
     * @param size
     * @return com.rumi.common.entity.Result<com.baomidou.mybatisplus.core.metadata.IPage < com.rumi.user.pojo.Address>>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: Address分页条件搜索实现
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result<IPage<Address>> findPage(@RequestBody(required = false) Address address, @PathVariable int page, @PathVariable int size) {
        IPage<Address> pageInfo = addressService.findPage(address, page, size);
        return new Result<IPage<Address>>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * @param page
     * @param size
     * @return com.rumi.common.entity.Result<com.baomidou.mybatisplus.core.metadata.IPage < com.rumi.user.pojo.Address>>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: Address分页搜索实现
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<IPage<Address>> findPage(@PathVariable int page, @PathVariable int size) {
        IPage<Address> pageInfo = addressService.findPage(page, size);
        return new Result<IPage<Address>>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * @param address
     * @return com.rumi.common.entity.Result<java.util.List < com.rumi.user.pojo.Address>>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: 多条件搜索
     */
    @PostMapping(value = "/search")
    public Result<List<Address>> findList(@RequestBody(required = false) Address address) {
        List<Address> list = addressService.findList(address);
        return new Result<List<Address>>(true, StatusCode.OK, "查询成功", list);
    }

    /**
     * @param id
     * @return com.rumi.common.entity.Result
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: 根据id删除
     */
    @DeleteMapping(value = "/{id}")
    public Result<String> delete(@PathVariable Integer id) {
        boolean remove = addressService.removeById(id);
        if (!remove) {
            return new Result<String>(false, StatusCode.ERROR, "删除失败");
        }
        return new Result<String>(true, StatusCode.OK, "删除成功");
    }

    /**
     * @param address
     * @param id
     * @return com.rumi.common.entity.Result
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: 修改
     */
    @PutMapping(value = "/{id}")
    public Result<String> update(@RequestBody Address address, @PathVariable Integer id) {
        address.setId(id);
        boolean update = addressService.updateById(address);
        if (!update) {
            return new Result<String>(false, StatusCode.ERROR, "修改失败");
        }
        return new Result<String>(true, StatusCode.OK, "修改成功");
    }

    /**
     * @param address
     * @return com.rumi.common.entity.Result
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: 添加
     */
    @PostMapping
    public Result<String> add(@RequestBody Address address) {
        boolean save = addressService.save(address);
        if (!save) {
            return new Result<String>(false, StatusCode.ERROR, "添加失败");
        }
        return new Result<String>(true, StatusCode.OK, "添加成功");
    }

    /**
     * @param id
     * @return com.rumi.common.entity.Result<com.rumi.user.pojo.Address>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: 根据id查询
     */
    @GetMapping("/{id}")
    public Result<Address> findById(@PathVariable Integer id) {
        Address address = addressService.getById(id);
        return new Result<Address>(true, StatusCode.OK, "查询成功", address);
    }

    /**
     * @return com.rumi.common.entity.Result<com.rumi.user.pojo.Address>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: 查询Address全部信息
     */
    @GetMapping
    public Result<List<Address>> findAll() {
        List<Address> list = addressService.list();
        return new Result<List<Address>>(true, StatusCode.OK, "查询成功", list);
    }
}