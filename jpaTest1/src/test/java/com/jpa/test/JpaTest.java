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
    EntityManagerFactory entityManagerFactory = null;
    EntityTransaction transaction = null;
    EntityManager entityManager = null;

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
}