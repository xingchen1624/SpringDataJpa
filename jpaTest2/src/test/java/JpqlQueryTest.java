/**
 * Copyright (C), 2012-2019, by xavier chen
 * FileName: JpqlQueryTest
 * Author:   chen
 * Date:     2019/8/12 21:25
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

/**
 * 〈一句话功能简述〉<br> 
 * 〈jpql语句测试类〉
 * @Author chen
 * @Date 2019/8/12
 * @since 1.0.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)  //声明spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml")  //指定spring容器的配置信息
public class JpqlQueryTest {
    @Autowired
    private CustomerDao customerDao;

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 根据客户名称查询客户信息
     * @Date 21:40 2019/8/12
     * @Param []
     * @return void
     **/
    @Test
    public void testFindCustomerByJpql(){
        Customer customer = customerDao.findCustomerByJpql("马云");
        System.out.println("customer = " + customer);
    }
    
    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 根据客户名称和客户id查询客户信息
     * @Date 21:45 2019/8/12
     * @Param []
     * @return void
     *
     **/
    @Test
    public void testFindCustomerByJpql2(){
        Customer customer = customerDao.findCustomerByNameAndCustId("张三丰", 2l);
        System.out.println("customer = " + customer);
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 根据id更新name
     * @Date 21:58 2019/8/12
     * @Param []
     * @return void
     *
     * spring data jpa使用jqpl进行更新或删除操作的时候，需要添加事务支持
     * 默认执行结束后会回滚当前操作
     * 使用@Rollback(value = false),让其默认不回滚
     **/
    @Test
    @Transactional  //添加事务支持
    @Rollback(value = false)
    public void testUpdateCustNameById(){
        customerDao.updateCustNameById(1, "浩克");
    }
}