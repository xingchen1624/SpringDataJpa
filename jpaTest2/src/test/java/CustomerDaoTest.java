/**
 * Copyright (C), 2012-2019, by xavier chen
 * FileName: CustomerDaoTest
 * Author:   chen
 * Date:     2019/8/8 21:31
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

import com.jpa.dao.CustomerDao;
import com.jpa.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈CustomerDao的测试类〉
 * 该测试类运行在spring的环境下
 *
 * @author chen
 * @createDate 2019/8/8
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)  //声明spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml")  //指定spring容器的配置信息
public class CustomerDaoTest {

    @Autowired
    private CustomerDao customerDao;

    /**
     * 功能描述: 根据id查询一个客户<br>
     *
     * @return void
     * @Author lzc
     * @Description
     * @Date 21:36 2019/8/8
     * @Param []
     **/
    @Test
    public void findOne() {
        Customer customer = customerDao.findOne(1L);
        System.out.println(customer);
    }

    /**
     * 在spring data jpa中save方法代表的是保存或者更新
     * 判断依据：传递的对象不包含主键则是保存，包含主键则是更新操作
     * 功能描述: <br>
     * @Author lzc
     * @Description
     * @Date 21:48 2019/8/8
     * @Param []
     * @return void
     **/
    @Test
    public void testSave(){
        Customer customer = new Customer();
        customer.setCustName("亚特兰蒂斯");
        customer.setCustSource("earth");
        customer.setCustLevel("S");
        customerDao.save(customer);
    }

    /**
     * 在spring data jpa中save方法代表的是保存或者更新
     * 判断依据：传递的对象不包含主键则是保存，包含主键则是更新操作
     * 功能描述: <br>
     * @Author lzc
     * @Description
     * @Date 21:48 2019/8/8
     * @Param []
     * @return void
     **/
    @Test
    public void testUpdate(){
        Customer customer = new Customer();
        customer.setCustId(4L);
        customer.setCustAddress("浙江省杭州市西湖区");
        customer.setCustIndustry("教师");
        customerDao.save(customer);
    }

    /**
     * 功能描述: 测试删除操作<br>
     * @Author lzc
     * @Description
     * @Date 21:56 2019/8/8
     * @Param []
     * @return void
     **/
    @Test
    public void testDelete(){
        customerDao.delete(4L);
    }

    /**
     * 功能描述: 测试查询全部<br>
     * @Author lzc
     * @Description
     * @Date 21:58 2019/8/8
     * @Param []
     * @return void
     **/
    @Test
    public void testFindAll(){
        List<Customer> customers = customerDao.findAll();
        for (Customer customer : customers) {
            System.out.println("customer = " + customer);
        }
    }
}