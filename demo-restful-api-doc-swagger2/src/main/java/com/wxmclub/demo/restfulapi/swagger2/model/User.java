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
package com.wxmclub.demo.restfulapi.swagger2.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 用户
 *
 * @author wxmclub@gmail.com
 * @version 1.0
 * @date 2018-05-07
 */
public class User {

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID", position = 1)
    private int id;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", position = 2)
    private String name;
    /**
     * 年龄
     */
    @ApiModelProperty(value = "年龄", position = 3)
    private int age;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", position = 4)
    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
