package com.jpa.dao;

import com.jpa.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

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

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 根据客户名称查询客户信息，使用jpql的方式
     * @Date 21:32 2019/8/12
     * @Param [custName]
     * @return com.jpa.entity.Customer
     *
     * 使用jpql语句，用@Query注释
     * 注意：我们的jpql语句使用?占位符的时候，区别于以前老的占位符用法，spring data jpa
     * 要求占位符后面要跟上对应的数字，比如这里的 ?0 ,诸如 ?1,?2等等
     **/
    @Query(value = "from Customer where custName = ?1 ")
    public Customer findCustomerByJpql(String custName);

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 根据客户名称和客户id查询客户信息
     * @Date 21:44 2019/8/12
     * @Param [name, id]
     * @return com.jpa.entity.Customer
     *
     * 对于多个占位符的情况，默认情况下，方法参数需要和占位符的位置对应
     * 如果想修改位置，可以使用  ?1 ?2指定对应位置的方法参数,像方法updateCustNameById中一样
     **/
    @Query(value = "from Customer where custName = ?1 and custId = ?2")
    public Customer findCustomerByNameAndCustId(String name,long id);

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 根据客户id更新客户的名称
     * @Date 21:51 2019/8/12
     * @Param [id]
     * @return void
     *
     * 其中@Modifying表示这是一个更新操作
     **/
    @Query(value="update Customer set custName=?2 where custId=?1")
    @Modifying
    public void updateCustNameById(long id,String name);

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 使用SQL的方式查询全部客户
     * @Date 22:08 2019/8/12
     * @Param []
     * @return java.util.List<com.jpa.entity.Customer>
     **/
    @Query(value = "select * from cst_customer",nativeQuery = true)
    public List<Customer> findAllBySql();

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 使用SQL的方式 更新客户级别通过客户id
     * @Date 22:08 2019/8/12
     * @Param []
     * @return void
     **/
    @Query(value = "update cst_customer set cust_level = ?1 where cust_id = ?2",nativeQuery = true)
    @Modifying
    public void updateCustLvById(String level,long id);

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 根据客户名称模糊查询客户信息
     * @Date 22:28 2019/8/12
     * @Param []
     * @return java.util.List<com.jpa.entity.Customer>
     **/
    @Query(value = "select * from cst_customer where cust_name like ?1",nativeQuery = true)
    public List<Customer> findCustomerByCustName(String name);

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 根据方法命名规范进行查询
     * @Date 20:02 2019/8/13
     * @Param 
     * @return
     *
     * 方法名的约定：
     *      findBy : 查询
     *          对象中的属性名 ： 查询条件
     *      eg：findByCustName : 根据客户名称查询
     *
     **/
    public Customer findByCustName(String name);

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 根据客户名称模糊查询
     * @Date 20:16 2019/8/13
     * @Param [name]
     * @return java.util.List<com.jpa.entity.Customer>
     **/
    public List<Customer> findByCustNameLike(String name);

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 根据客户名称模糊查询、客户行业精确查询客户
     * @Date 20:23 2019/8/13
     * @Param [custName, custIndustry]
     * @return java.util.List<com.jpa.entity.Customer>
     **/
    public List<Customer> findByCustNameLikeAndCustIndustry(String custName,String custIndustry);
}
