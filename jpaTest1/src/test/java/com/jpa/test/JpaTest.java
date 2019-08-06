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
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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
            entityManagerFactory = Persistence.createEntityManagerFactory("myJpa");
            //2.通过实体管理类工厂获取实体管理器
            entityManager = entityManagerFactory.createEntityManager();
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
            entityManagerFactory.close();
        }

    }
}