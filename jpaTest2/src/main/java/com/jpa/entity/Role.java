/**
 * Copyright (C), 2012-2019, by xavier chen
 * FileName: Role
 * Author:   chen
 * Date:     2019/8/14 21:48
 * Description: 多对多映射实体类 - 角色
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
 * 〈多对多映射实体类 - 角色〉
 * @Author chen
 * @Date 2019/8/14
 * @since 1.0.0
 **/
@Entity
@Table(name = "sys_role")
public class Role {
    private Long roleId;
    private String roleName;
    private Set<User> users = new HashSet<User>();  //角色关联的用户对象

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Column(name = "role_name",length = 30)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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
     * @ManyToMany(mappedBy = "roles")  : role一方放弃维护外键关系，由user维护。
     * 只使用该配置即可，不用再配置（注释掉的代码）
     **/
    /*@ManyToMany(targetEntity = User.class)
    @JoinTable(
            name = "sys_user_role",
            joinColumns = {@JoinColumn(name = "sys_role_id",referencedColumnName = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "sys_user_id",referencedColumnName = "user_id")}
    )*/
    @ManyToMany(mappedBy = "roles")
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}