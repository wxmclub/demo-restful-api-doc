/*
MIT License

Copyright (c) 2018 wxmclub@gmail.com

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package com.wxmclub.demo.restfulapi.swagger2.controller;

import com.wxmclub.demo.restfulapi.swagger2.model.JsonResult;
import com.wxmclub.demo.restfulapi.swagger2.model.User;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wxmclub@gmail.com
 * @version 1.0
 * @date 2018-05-07
 */
@Api("用户Controller")
@RestController
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private static Map<Integer, User> users = new ConcurrentHashMap<>();
    private static AtomicInteger idAtomic = new AtomicInteger(1);

    static {
        log.info("初始化用户");
        // 初始添加5个用户
        String[] names = new String[]{"Tom", "Jack", "Marry", "Tim", "Dick"};
        int[] ages = new int[]{18, 20, 24, 22, 28};
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setId(idAtomic.getAndIncrement());
            user.setName(names[i]);
            user.setAge(ages[i]);
            user.setCreateTime(new Date());
            users.put(user.getId(), user);
        }
    }

    /**
     * 添加用户
     *
     * @param user 用户
     * @return 用户ID
     */
    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    //@ApiModel()
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public JsonResult<Integer> add(@RequestBody User user) {
        JsonResult<Integer> r = new JsonResult<>();
        if (user == null || user.getName() == null) {
            r.setCode("ERROR");
            r.setMessage("用户或用户名为空");
        } else {
            user.setId(idAtomic.getAndIncrement());
            user.setCreateTime(new Date());
            users.put(user.getId(), user);
            r.setCode("SUCCESS");
            r.setData(user.getId());
        }
        return r;
    }

    /**
     * 根据ID查询用户
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public JsonResult<User> getUserById(@PathVariable(value = "id") Integer id) {
        JsonResult<User> r = new JsonResult<>();
        User user = users.get(id);
        if (user != null) {
            r.setCode("SUCCESS");
            r.setData(user);
        } else {
            r.setCode("ERROR");
            r.setMessage("用户不存在");
        }
        return r;
    }

    /**
     * 根据id修改用户信息
     *
     * @param user
     * @return
     */
    @ApiOperation(value = "更新信息", notes = "根据url的id来指定更新用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "user", value = "用户实体user", required = true, dataType = "User")
    })
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public JsonResult<User> update(@PathVariable("id") Integer id, @RequestBody User user) {
        JsonResult<User> r = new JsonResult<>();
        User u = users.get(id);
        if (u == null) {
            r.setCode("ERROR");
            r.setMessage("用户不存在");
        } else if (user == null || user.getName() == null) {
            r.setCode("ERROR");
            r.setMessage("更新信息为空");
        } else {
            u.setName(user.getName());
            u.setAge(user.getAge());
            users.put(u.getId(), u);
            r.setCode("SUCCESS");
            r.setData(user);
        }
        return r;
    }

    /**
     * 根据id删除用户
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public JsonResult<Integer> delete(@PathVariable(value = "id") Integer id) {
        JsonResult<Integer> r = new JsonResult<>();
        if (users.remove(id) == null) {
            r.setCode("ERROR");
            r.setMessage("用户不存在");
        } else {
            r.setCode("SUCCESS");
            r.setData(id);
        }
        return r;
    }

    /**
     * 查询用户列表
     *
     * @return
     */
    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public JsonResult<List<User>> getUserList() {
        JsonResult<List<User>> r = new JsonResult<>();
        List<User> userList = new ArrayList<>(users.values());
        r.setCode("SUCCESS");
        r.setData(userList);
        return r;
    }

    @ApiIgnore//使用该注解忽略这个API
    @RequestMapping(value = "/user/hi", method = RequestMethod.GET)
    public String jsonTest() {
        return " hi you!";
    }

}
