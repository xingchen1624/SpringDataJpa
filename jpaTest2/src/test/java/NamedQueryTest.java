/**
 * Copyright (C), 2012-2019, by xavier chen
 * FileName: NamedQueryTest
 * Author:   chen
 * Date:     2019/8/13 20:05
 * Description: 根据方法命名规则查询测试
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

import com.jpa.dao.CustomerDao;
import com.jpa.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈根据方法命名规则查询测试〉
 * 说明：
 * 1.findBy : 查询（默认情况下使用等于查询条件）
 *      * 对象中的属性名 ： 查询条件
 *      *  eg：findByCustName : 根据客户名称查询
 * 2.findBy + 属性名称 + 查询方式（like | isnull 等等）
 * eg:findByCustNameLike
 * 3.多条件查询
 *      findBy + 属性名称 + 查询方式 + 多条件连接符（and | or）+ 属性名称 + 查询方式
 *
 * spring data jpa会在运行阶段解析方法名生成对应的sql
 *
 * @Author chen
 * @Date 2019/8/13
 * @since 1.0.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)  //声明spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml")  //指定spring容器的配置信息
public class NamedQueryTest {
    @Autowired
    private CustomerDao customerDao;

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description
     * //TODO 方法名的约定：
     * findBy : 查询
     * 对象中的属性名 ： 查询条件
     *  eg：findByCustName : 根据客户名称查询
     * @Date 20:08 2019/8/13
     * @Param []
     * @return void
     **/
    @Test
    public void testNamedQuery(){
        Customer customer = customerDao.findByCustName("张三丰");
        System.out.println("customer = " + customer);
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 根据客户名称模糊查询客户
     * @Date 20:18 2019/8/13
     * @Param []
     * @return void
     **/
    @Test
    public void testNamedQueryLike(){
        List<Customer> customerlist = customerDao.findByCustNameLike("张%");
        for (Customer customer : customerlist) {
            System.out.println("customer = " + customer);
        }
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 根据客户名称模糊查询、客户行业精确查询客户
     * @Date 20:26 2019/8/13
     * @Param []
     * @return void
     **/
    @Test
    public void testNamedQueryManyCondition(){
        List<Customer> customerlist = customerDao.findByCustNameLikeAndCustIndustry("张%","IT");
        for (Customer customer : customerlist) {
            System.out.println("customer = " + customer);
        }
    }
}