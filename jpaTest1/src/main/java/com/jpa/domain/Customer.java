/**
 * Copyright (C), 2012-2019, by xavier chen
 * FileName: Customer
 * Author:   chen
 * Date:     2019/8/5 22:15
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jpa.domain;

import javax.persistence.*;

/**
 * 〈一句话功能简述〉<br> 
 * 〈测试spring data jpa使用的实体类〉
 *
 * @author chen
 * @createDate 2019/8/5
 * @since 1.0.0
 *
 * @Entity:声明该类是一个实体类
 * @Table:配置实体类和表的映射关系
 */
@Entity
@Table(name = "cst_customer")
public class Customer {
    /**
     * ALT+C 给变量方法加注释
     * spring data jpa主键生成策略主要有如下几种
     * strategy = GenerationType.IDENTITY ； 自动增长，数据库要支持自动增长
     * strategy = GenerationType.TABLE ：使用一个特定的数据库表格来保存主键
     * strategy = GenerationType.AUTO ：主键由程序控制
     * strategy = GenerationType.SEQUENCE ：由序列生成主键，数据库要支持序列
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //主键生成策略
    @Column(name = "cust_id")
    private Long id;

    @Column(name = "cust_name")
    private String custName;

    @Column(name = "cust_source")
    private String custSource;

    @Column(name = "cust_level")
    private String custLevel;

    @Column(name = "cust_industry")
    private String custIndustry;

    @Column(name = "cust_phone")
    private String custPhone;

    @Column(name = "cust_address")
    private String custAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", custName='" + custName + '\'' +
                ", custSource='" + custSource + '\'' +
                ", custLevel='" + custLevel + '\'' +
                ", custIndustry='" + custIndustry + '\'' +
                ", custPhone='" + custPhone + '\'' +
                ", custAddress='" + custAddress + '\'' +
                '}';
    }
}