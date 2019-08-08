package com.jpa.dao;

import com.jpa.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 功能描述: <br>
 * 符合spring data jpa规范的dao层接口定义要满足如下要求
 * ①继承JpaRepository和JpaSpecificationExecutor接口
 * ②并给这两个接口提供相应的泛型JpaRepository<操作的实体类类型, 操作的实体类主键的类型>
 * JpaSpecificationExecutor<操作的实体类类型>
 * 我们只要定义好这个接口，就可以使用spring data jpa做基本的增删查改了，因为
 * JpaRepository接口封装了基本的增删改查功能
 * JpaSpecificationExecutor封装了复杂动态查询，分页查询等功能
 **/
public interface CustomerDao extends
        JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
}
