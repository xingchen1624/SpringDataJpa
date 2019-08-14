/**
 * Copyright (C), 2012-2019, by xavier chen
 * FileName: One2ManyTest
 * Author:   chen
 * Date:     2019/8/14 20:54
 * Description: jpa一对多，多对多等映射关系配置测试类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

import com.jpa.dao.CustomerDao;
import com.jpa.dao.LinkManDao;
import com.jpa.dao.RoleDao;
import com.jpa.dao.UserDao;
import com.jpa.entity.Customer;
import com.jpa.entity.LinkMan;
import com.jpa.entity.Role;
import com.jpa.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 〈一句话功能简述〉
 * 〈jpa一对多，多对多等映射关系配置测试类〉
 * @Author chen
 * @Date 2019/8/14
 * @since 1.0.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)  //声明spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml")  //指定spring容器的配置信息
public class One2ManyTest {
    //一对多使用的dao接口
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private LinkManDao linkManDao;

    //多对多使用的dao接口
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 测试一对多映射
     * @Date 20:56 2019/8/14
     * @Param []
     * @return void
     **/
    @Test
    @Transactional //配置事务
    @Rollback(value = false)
    public void testAdd(){
        //创建客户和联系人
        Customer customer = new Customer();
        customer.setCustName("百度");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("江流儿");

        /**
         * 建立关联关系的时候，任何一方建立关联关系就可以。当然两方都设置也可以
         * 但推荐使用多的一方来设置关联关系，一的一方放弃维护外键关系(一的一方使用mappedBy)
         * 一的一方放弃维护外键关系后，
         **/
        //建立关联关系
        //customer.getLinkManSet().add(linkMan);

        /*
         * 也可以使用这种方式(一的一方放弃维护外键关系，只能使用下面这种关系设置方式)
         * 如果两方都维护外键关系，那么上面的方式也可以用
         **/
        linkMan.setCustomer(customer);

        customerDao.save(customer);
        linkManDao.save(linkMan);
    }


    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 测试级联保存
     * @Date 21:24 2019/8/14
     * @Param []
     * @return void
     **/
    @Test
    @Transactional //配置事务
    @Rollback(value = false)
    public void testCascadeAdd(){
        //创建客户和联系人
        Customer customer = new Customer();
        customer.setCustName("腾讯");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("麻花藤");

        //设置关联关系
        customer.getLinkManSet().add(linkMan);
        linkMan.setCustomer(customer);

        customerDao.save(customer);  //只保存客户看能否级联保存联系人
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 测试级联删除
     * @Date 21:28 2019/8/14
     * @Param []
     * @return void
     **/
    @Test
    @Transactional //配置事务
    @Rollback(value = false)
    public void testCascadeRemove(){
        //查询待删除的客户
        Customer customer = customerDao.findOne(2l);
        //删除该客户
        customerDao.delete(customer);

    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 测试多对多的新增
     * @Date 22:15 2019/8/14
     * @Param []
     * @return void
     *
     * 多对多的关系当中，被动的一方放弃维护外键关系
     **/
    @Test
    @Transactional //配置事务
    @Rollback(value = false)
    public void testM2MAdd(){
        //创建客户和联系人
        User user = new User();
        user.setUserName("jery");

        Role role = new Role();
        role.setRoleName("java程序员");

        //如果一方不放弃维护外键关系，这里双向关系设置后，中间表报主键冲突的错误
        //配置用户到角色的关系，可以对中间表中的数据进行维护
        user.getRoles().add(role);
        //配置角色到用户的关系，可以对中间表中的数据进行维护
        role.getUsers().add(user);

        userDao.save(user);
        roleDao.save(role);
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 测试多对多的级联新增
     * @Date 22:30 2019/8/14
     * @Param []
     * @return void
     **/
    @Test
    @Transactional //配置事务
    @Rollback(value = false)
    public void testM2MCascadeAdd(){
        //创建客户和联系人
        User user = new User();
        user.setUserName("jery");

        Role role = new Role();
        role.setRoleName("java程序员");

        //配置用户到角色的关系，可以对中间表中的数据进行维护
        user.getRoles().add(role);

        userDao.save(user);
    }


    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 测试多对多的级联删除
     * @Date 22:30 2019/8/14
     * @Param []
     * @return void
     **/
    @Test
    @Transactional //配置事务
    @Rollback(value = false)
    public void testM2MCascadeRemove(){
        //查询待删除用户
        User user = userDao.findOne(1l);
        //删除用户
        userDao.delete(user);
    }
}