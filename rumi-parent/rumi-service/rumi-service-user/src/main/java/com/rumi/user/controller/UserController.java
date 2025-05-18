package com.rumi.user.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rumi.common.entity.BCrypt;
import com.rumi.common.entity.JwtUtil;
import com.rumi.common.entity.Result;
import com.rumi.common.entity.StatusCode;
import com.rumi.user.pojo.User;
import com.rumi.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author:CSH
 * @Updator:CSH
 * @Date 2025/5/17 22:38
 * @Description: User管理控制层
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private IUserService userService;

    /**
     * @param user
     * @param page
     * @param size
     * @return com.rumi.common.entity.Result<com.baomidou.mybatisplus.core.metadata.IPage < com.rumi.user.pojo.User>>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: User分页条件搜索实现
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result<IPage<User>> findPage(@RequestBody(required = false) User user, @PathVariable int page, @PathVariable int size) {
        IPage<User> pageInfo = userService.findPage(user, page, size);
        return new Result<IPage<User>>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * @param page
     * @param size
     * @return com.rumi.common.entity.Result<com.baomidou.mybatisplus.core.metadata.IPage < com.rumi.user.pojo.User>>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: User分页搜索实现
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<IPage<User>> findPage(@PathVariable int page, @PathVariable int size) {
        IPage<User> pageInfo = userService.findPage(page, size);
        return new Result<IPage<User>>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * @param user
     * @return com.rumi.common.entity.Result<java.util.List < com.rumi.user.pojo.User>>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: 多条件搜索
     */
    @PostMapping(value = "/search")
    public Result<List<User>> findList(@RequestBody(required = false) User user) {
        List<User> list = userService.findList(user);
        return new Result<List<User>>(true, StatusCode.OK, "查询成功", list);
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
    public Result<String> delete(@PathVariable String id) {
        boolean remove = userService.removeById(id);
        if (!remove) {
            return new Result<String>(false, StatusCode.ERROR, "删除失败");
        }
        return new Result<String>(true, StatusCode.OK, "删除成功");
    }

    /**
     * @param user
     * @param id
     * @return com.rumi.common.entity.Result
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: 修改
     */
    @PutMapping(value = "/{id}")
    public Result<String> update(@RequestBody User user, @PathVariable String id) {
        user.setUsername(id);
        boolean update = userService.updateById(user);
        if (!update) {
            return new Result<String>(false, StatusCode.ERROR, "修改失败");
        }
        return new Result<String>(true, StatusCode.OK, "修改成功");
    }

    /**
     * @param user
     * @return com.rumi.common.entity.Result
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: 添加
     */
    @PostMapping
    public Result<String> add(@RequestBody User user) {
        boolean save = userService.save(user);
        if (!save) {
            return new Result<String>(false, StatusCode.ERROR, "添加失败");
        }
        return new Result<String>(true, StatusCode.OK, "添加成功");
    }

    /**
     * @param id
     * @return com.rumi.common.entity.Result<com.rumi.user.pojo.User>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: 根据id查询
     */
    @GetMapping("/{id}")
    public Result<User> findById(@PathVariable String id) {
        User user = userService.getById(id);
        return new Result<User>(true, StatusCode.OK, "查询成功", user);
    }

    /**
     * @return com.rumi.common.entity.Result<java.util.List < com.rumi.user.pojo.User>>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: 查询User全部信息
     */
    @GetMapping
    public Result<List<User>> findAll() {
        List<User> list = userService.list();
        return new Result<List<User>>(true, StatusCode.OK, "查询成功", list);
    }

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @param response 响应对象
     * @param request 请求对象
     * @return 登录结果
     */
    @GetMapping("/login")
    public Result<String> login(String username, String password, HttpServletResponse response, HttpServletRequest request) {
        // 1.从数据库中查询用户名对应的用户的对象
        User user = userService.getById(username);
        if (user == null) {
            // 2.判断用户是否为空 为空返回数据
            return new Result<String>(false, StatusCode.LOGINERROR, "用户名或密码错误");
        }

        // 3.如果不为空 判断 密码是否正确 正确则登录成功
        if (BCrypt.checkpw(password, user.getPassword())) {
            // 成功
            Map<String, Object> info = new HashMap<>();
            info.put("role", "USER");
            info.put("success", "SUCCESS");
            info.put("username", username);

            // 1.生成令牌
            String jwt = JwtUtil.createJWT(UUID.randomUUID().toString(), JSON.toJSONString(info), null);
            // 2.设置cookie中
            Cookie cookie = new Cookie("Authorization", jwt);
            response.addCookie(cookie);
            // 3.设置头文件中
            response.setHeader("Authorization", jwt);

            return new Result<String>(true, StatusCode.OK, "成功", jwt);
        } else {
            // 失败
            return new Result<String>(false, StatusCode.LOGINERROR, "用户名或密码错误");
        }
    }
}