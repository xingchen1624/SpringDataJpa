/**
 * Copyright (C), 2012-2019, by xavier chen
 * FileName: JpaTest
 * Author:   chen
 * Date:     2019/8/5 22:33
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jpa.test;

import com.jpa.domain.Customer;
import com.jpa.util.JpaUtil;
import org.junit.Test;

import javax.persistence.*;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈jpa测试类〉
 *
 * @author chen
 * @createDate 2019/8/5
 * @since 1.0.0
 */
public class JpaTest {
    private EntityManagerFactory entityManagerFactory = null;
    private EntityTransaction transaction = null;
    private EntityManager entityManager = null;

    /**
     * 功能描述: <br>
     *     使用jpa测试保存功能
     **/
    @Test
    public void testSave(){
        try {
            //1.加载配置文件创建工厂（实体管理类工厂）
            //entityManagerFactory = Persistence.createEntityManagerFactory("myJpa");
            //2.通过实体管理类工厂获取实体管理器
            //entityManager = entityManagerFactory.createEntityManager();
            entityManager = JpaUtil.getEntityManager();
            //3.获取事务对象，开启事务
            transaction = entityManager.getTransaction();
            transaction.begin(); //开启事务
            //4.完成增删改查操作
            Customer customer = new Customer();
            customer.setCustName("传智播客");
            customer.setCustPhone("10080");
            customer.setCustAddress("江苏省常州市金坛区");
            entityManager.persist(customer); //保存
            //5.事务提交或回滚
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            //6.释放相应资源
            entityManager.close();
            //entityManagerFactory.close();  //使用工具类后这里不能关闭，否则其它方法无法使用
        }

    }

    /**
     * 根据id查询客户，使用find方法
     *   1.获取的是Customer对象
     *   2.在调用find方法时，即发出查询语句，立即加载
     */
    @Test
    public void testFind(){
        try {
            //通过实体管理类工厂获取实体管理器
            entityManager = JpaUtil.getEntityManager();
            //获取事务对象，开启事务
            transaction = entityManager.getTransaction();
            transaction.begin(); //开启事务
            //完成查询操作
            Customer customer = entityManager.find(Customer.class,2L);
            System.out.println(customer);
            //事务提交或回滚
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            entityManager.close();
        }
    }

    /**
     * 根据id查询客户 getReference
     *  1.获取的对象是Customer的动态代理对象
     *  2.调用getReference方法的时候没有发送查询语句，而是在实际使用的时候才发送查询语句，即延迟加载
     */
    @Test
    public void testReference(){
        try {
            //通过实体管理类工厂获取实体管理器
            entityManager = JpaUtil.getEntityManager();
            //获取事务对象，开启事务
            transaction = entityManager.getTransaction();
            transaction.begin(); //开启事务
            //完成查询操作
            Customer customer = entityManager.getReference(Customer.class,1L);
            System.out.println(customer);
            //事务提交或回滚
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            entityManager.close();
        }
    }

    /**
     * 根据id删除一个客户
     */
    @Test
    public void testRemove(){
        try {
            //通过实体管理类工厂获取实体管理器
            entityManager = JpaUtil.getEntityManager();
            //获取事务对象，开启事务
            transaction = entityManager.getTransaction();
            transaction.begin(); //开启事务
            //完成删除操作
            Customer customer = entityManager.find(Customer.class, 1L);
            entityManager.remove(customer);
            //事务提交或回滚
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            entityManager.close();
        }
    }

    /**
     * 更新一个客户
     */
    @Test
    public void testUpdate(){
        try {
            //通过实体管理类工厂获取实体管理器
            entityManager = JpaUtil.getEntityManager();
            //获取事务对象，开启事务
            transaction = entityManager.getTransaction();
            transaction.begin(); //开启事务
            //完成更新操作
            Customer customer = entityManager.find(Customer.class, 2L);
            customer.setCustName("张三丰");
            customer.setCustLevel("A");
            customer.setCustIndustry("IT");
            entityManager.merge(customer);
            //事务提交或回滚
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            entityManager.close();
        }
    }

    /**
     * 查询全部
     * sql:select * from cst_customer
     * jpql:from Customer
     */
    @Test
    public void testJpqlFindAll(){
        try {
            //通过实体管理类工厂获取实体管理器
            entityManager = JpaUtil.getEntityManager();
            //获取事务对象，开启事务
            transaction = entityManager.getTransaction();
            transaction.begin(); //开启事务
            //完成查询操作
            //jpql查询全部有如下两种写法
            //String jpql = "select c from Customer c";
            String jpql = "from Customer";
            Query query = entityManager.createQuery(jpql);

            //发送查询，并封装结果
            List resultList = query.getResultList();

            for (Object obj : resultList){
                System.out.println(obj);
            }

            //事务提交或回滚
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            entityManager.close();
        }
    }

    /*
     * 功能描述:查询客户信息并按客户id倒序排列 <br>
     * @Author lzc
     * @Description
     * @Date 20:12 2019/8/7
     * @Param []
     * @return void
     * sql:select * from cst_customer order by id desc
     * jpql:from Customer order by id desc
     **/
    @Test
    public void testJpqlQueryOrder(){
        try {
            //通过实体管理类工厂获取实体管理器
            entityManager = JpaUtil.getEntityManager();
            //获取事务对象，开启事务
            transaction = entityManager.getTransaction();
            transaction.begin(); //开启事务
            //完成查询操作
            String jpql = "from Customer order by id desc";
            Query query = entityManager.createQuery(jpql);

            //发送查询，并封装结果
            List resultList = query.getResultList();

            for (Object obj : resultList){
                System.out.println(obj);
            }

            //事务提交或回滚
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            entityManager.close();
        }
    }

    /**
     * 功能描述: 统计客户的总人数<br>
     * @Author lzc
     * @Description
     * @Date 20:24 2019/8/7
     * @Param []
     * @return void
     * sql:select count(cust_id) from cst_customer
     * jpql:select count(id) from Customer
     **/
    @Test
    public void testJpqlCount(){
        try {
            //通过实体管理类工厂获取实体管理器
            entityManager = JpaUtil.getEntityManager();
            //获取事务对象，开启事务
            transaction = entityManager.getTransaction();
            transaction.begin(); //开启事务
            //完成查询操作
            String jpql = "select count(id) from Customer";
            //创建Query查询对象
            Query query = entityManager.createQuery(jpql);

            //发送查询，并封装结果
            Object singleResult = query.getSingleResult();  //得到唯一结果

            System.out.println("客户总人数 = " + singleResult);

            //事务提交或回滚
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            entityManager.close();
        }
    }

    /**
     * 功能描述: 分页查询客户<br>
     * @Author lzc
     * @Description
     * @Date 20:28 2019/8/7
     * @Param []
     * @return void
     * sql:select * from cst_customer limit ?,?
     **/
    @Test
    public void testJpqlPage(){
        try {
            //通过实体管理类工厂获取实体管理器
            entityManager = JpaUtil.getEntityManager();
            //获取事务对象，开启事务
            transaction = entityManager.getTransaction();
            transaction.begin(); //开启事务
            //完成查询操作
            String jpql = "from Customer";
            //创建Query查询对象
            Query query = entityManager.createQuery(jpql);
            //设置分页对象
            query.setFirstResult(1);  //起始索引
            query.setMaxResults(3);  //每页查询条数

            //发送查询，并封装结果
            List resultList = query.getResultList();

            for (Object o : resultList) {
                System.out.println("o = " + o);
            }

            //事务提交或回滚
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            entityManager.close();
        }
    }

    /**
     * 功能描述: 条件查询 - 查询客户名称以'张'开头的客户<br>
     * @Author lzc
     * @Description
     * @Date 20:51 2019/8/7
     * @Param []
     * @return void
     * sql:select * from cst_customer where cust_name like '%张%'
     * jpql同样支持占位符
     * jpql:from Customer where custName like ?
     **/
    @Test
    public void testJpqlWhere(){
        try {
            //通过实体管理类工厂获取实体管理器
            entityManager = JpaUtil.getEntityManager();
            //获取事务对象，开启事务
            transaction = entityManager.getTransaction();
            transaction.begin(); //开启事务
            //完成查询操作
            String jpql = "from Customer where custName like ?";
            //创建Query查询对象
            Query query = entityManager.createQuery(jpql);
            //对占位符进行赋值,占位符索引位置从1开始
            query.setParameter(1,"%张%");

            //发送查询，并封装结果
            List resultList = query.getResultList();

            for (Object o : resultList) {
                System.out.println("o = " + o);
            }

            //事务提交或回滚
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            entityManager.close();
        }
    }

}