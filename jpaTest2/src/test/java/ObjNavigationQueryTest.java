/**
 * Copyright (C), 2012-2019, by xavier chen
 * FileName: ObjNavigationQueryTest
 * Author:   chen
 * Date:     2019/8/15 19:08
 * Description: jpa的对象导航查询测试类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

import com.jpa.dao.CustomerDao;
import com.jpa.dao.LinkManDao;
import com.jpa.entity.Customer;
import com.jpa.entity.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * 〈一句话功能简述〉
 * 〈jpa的对象导航查询测试类〉
 * @Author chen
 * @Date 2019/8/15
 * @since 1.0.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)  //声明spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml")  //指定spring容器的配置信息
public class ObjNavigationQueryTest {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private LinkManDao linkManDao;

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 测试对象导航查询，根据客户查询客户的关联联系人
     * @Date 19:11 2019/8/15
     * @Param []
     * @return void
     *
     * 对象导航查询默认使用延迟加载，在使用关联对象的时候才发送查询语句
     * 如果要关闭延迟加载，只需要修改配置fetch，将延迟加载改为立即加载
     * 从一的一方查询多的一方，默认情况下使用延迟加载。即不配置fetch属性
     **/
    @Transactional  //开启事务，可以解决no_session错误
    @Test
    public void testObjNavQuery(){
        //查询客户
        Customer customer = customerDao.findOne(1l);
        //根据客户获取联系人
        Set<LinkMan> linkManSet = customer.getLinkManSet();

        for (LinkMan linkMan : linkManSet) {
            System.out.println("linkMan = " + linkMan);
        }

    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 对象导航查询 根据联系人查询客户
     * @Date 19:21 2019/8/15
     * @Param []
     * @return void
     *
     * 从多的一方查询一的一方，默认情况下使用立即加载。即不配置fetch属性
     **/
    @Transactional  //开启事务，可以解决no_session错误
    @Test
    public void testObjNavQuery2(){
        //查询联系人
        LinkMan linkMan1 = linkManDao.findOne(2l);
        //根据联系人查询客户
        Customer customer = linkMan1.getCustomer();

        System.out.println(customer);

    }
}