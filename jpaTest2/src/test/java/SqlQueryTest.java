/**
 * Copyright (C), 2012-2019, by xavier chen
 * FileName: SqlQueryTest
 * Author:   chen
 * Date:     2019/8/12 22:02
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

import com.jpa.dao.CustomerDao;
import com.jpa.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈测试spring data jpa中的sql查询用法〉
 * @Author chen
 * @Date 2019/8/12
 * @since 1.0.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)  //声明spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml")  //指定spring容器的配置信息
public class SqlQueryTest {
    @Autowired
    private CustomerDao customerDao;
    
    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 查询全部客户 - SQL方式
     * @Date 22:13 2019/8/12
     * @Param []
     * @return void
     **/
    @Test
    public void testFindAllBySql(){
        List<Customer> customers = customerDao.findAllBySql();
        for (Customer customer : customers) {
            System.out.println("customer = " + customer);
        }
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 根据客户id更新客户等级
     * @Date 22:28 2019/8/12
     * @Param []
     * @return void
     **/
    @Test
    @Transactional
    @Rollback(value = false)
    public void testUpdateById(){
        customerDao.updateCustLvById("Z",1l);
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 根据客户名称模糊查询
     * @Date 22:31 2019/8/12
     * @Param []
     * @return void
     **/
    @Test
    public void testFindCustByNameLike(){
        List<Customer> customers = customerDao.findCustomerByCustName("%张%");
        for (Customer customer : customers) {
            System.out.println("customer = " + customer);
        }
    }
}