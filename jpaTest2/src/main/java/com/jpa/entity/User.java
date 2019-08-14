/**
 * Copyright (C), 2012-2019, by xavier chen
 * FileName: User
 * Author:   chen
 * Date:     2019/8/14 21:48
 * Description: 多对多映射关系实体类 - 用户
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/
package com.jpa.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 〈一句话功能简述〉
 * 〈多对多映射关系实体类 - 用户〉
 * @Author chen
 * @Date 2019/8/14
 * @since 1.0.0
 **/
@Entity
@Table(name = "sys_user")
public class User {
    private Long userId;
    private String userName;
    private Integer age;
    private Set<Role> roles = new HashSet<Role>();  //用户关联的角色信息

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Column(name = "user_name",length = 30)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "age",length = 3)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 配置多对多的关联关系
     * 1.声明表关系
     *      @ManyToMany ：多对多
     *      targetEntity:关联对象的class
     * 2.配置中间表
     *      @JoinTable : 配置中间表
     *          name : 中间表名称
     *          joinColumns ：当前对象在中间表的外键
     *              name ：当前对象在中间表中对应的外键字段名称
     *              referencedColumnName ：该外键字段参照的字段名称
     *          inverseJoinColumns ：关联对象在中间表的外键
     *              name ：关联对象在中间表中对应的外键字段名称
     *              referencedColumnName ：该外键字段参照的字段名称
     *
     * cascade : 级联操作
     **/
    @ManyToMany(targetEntity = Role.class,cascade = CascadeType.ALL)
    @JoinTable(
            name = "sys_user_role",
            joinColumns = {@JoinColumn(name = "sys_user_id",referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "sys_role_id",referencedColumnName = "role_id")}
    )
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}