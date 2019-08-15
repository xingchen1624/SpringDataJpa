/**
 * Copyright (C), 2012-2019, by xavier chen
 * FileName: Customer
 * Author:   chen
 * Date:     2019/8/8 21:01
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jpa.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 〈一句话功能简述〉<br> 
 * 〈spring data jpa的实体类 - Customer〉
 *
 * @author chen
 * @createDate 2019/8/8
 * @since 1.0.0
 */
@Entity
@Table(name = "cst_customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //主键生成策略
    @Column(name = "cust_id")
    private Long custId;

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

    //客户持有的联系人/**
    //     * 使用注解配置一对多关系
    //     *  1.声明关系
    //     *      一的一方使用 @OneToMany配置一对多关系
    //     *          targetEntity：关联的多的一方的class
    //     *  2.配置外键
    //     *      @JoinColumn ：配置外键
    //     *          name:外键字段名称
    //     *          referencedColumnName : 参照的主表的字段名称
    //     **/集合

    /**
     * 一的一方放弃外键的维护，交由多的一方维护
     *      mappedBy:交给多的一方的属性 ‘customer’来维护外键关系
     *
     * cascade:设置级联操作  CascadeType.ALL表示所有操作都使用级联
     * fetch ：加载策略  FetchType.EAGER立即加载  LAZY:延迟加载。一般建议使用延迟加载
     **/
//    @OneToMany(targetEntity = LinkMan.class)
//    @JoinColumn(name = "lkm_cust_id",referencedColumnName = "cust_id")
    @OneToMany(mappedBy = "customer",cascade=CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<LinkMan> linkManSet = new HashSet<LinkMan>();

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
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

    public Set<LinkMan> getLinkManSet() {
        return linkManSet;
    }

    public void setLinkManSet(Set<LinkMan> linkManSet) {
        this.linkManSet = linkManSet;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                ", custSource='" + custSource + '\'' +
                ", custLevel='" + custLevel + '\'' +
                ", custIndustry='" + custIndustry + '\'' +
                ", custPhone='" + custPhone + '\'' +
                ", custAddress='" + custAddress + '\'' +
                '}';
    }
}