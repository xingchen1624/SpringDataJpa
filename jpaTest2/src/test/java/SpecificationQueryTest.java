/**
 * Copyright (C), 2012-2019, by xavier chen
 * FileName: SpecificationQueryTest
 * Author:   chen
 * Date:     2019/8/13 21:12
 * Description: 使用JpaSpecificationExecutor进行动态查询
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

import com.jpa.dao.CustomerDao;
import com.jpa.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.Iterator;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈使用JpaSpecificationExecutor进行动态查询〉
 * @Author chen
 * @Date 2019/8/13
 * @since 1.0.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)  //声明spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml")  //指定spring容器的配置信息
public class SpecificationQueryTest {
    @Autowired
    private CustomerDao customerDao;
    
    @Test
    public void testFindOne(){
        /*
         * 使用匿名内部类创建Specification对象,要提供泛型，查询的对象类型
         * 实现toPredicate方法，构造查询条件
         * 需要借助方法中的参数
         *    root    ：Root接口，代表查询的根对象，可以通过root获取实体中的属性
         *    query    ：代表一个顶层查询对象，用来自定义查询
         *    cb        ：用来构建查询，此对象里有很多条件方法
         * 查询案例：根据客户名称查询客户
         *      1.查询方式 ：cb对象
         *      2.比较的属性名称 ：root对象
         **/
        Specification<Customer> specification = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //获取查询条件
                Path<Object> custName = root.get("custName");
                //构造查询方式(equal:精准匹配)
                Predicate predicate = cb.equal(custName, "张无忌");
                return predicate;

            }
        };
        Customer customer = customerDao.findOne(specification);
        System.out.println("customer = " + customer);
    }
    
    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 根据客户名称和客户所属行业查询客户
     * @Date 21:37 2019/8/13
     * @Param []
     * @return void
     **/
    @Test
    public void testFindByManyCondition(){
       /* root:获取属性
                客户名称
                所属行业
        cb:构造查询条件
            1.构造客户名称和客户所属行业的精准匹配查询
            2.将两个查询条件连接起来
        */
        Specification<Customer> specification = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //获取查询条件
                Path<Object> custName = root.get("custName");
                Path<Object> custIndustry = root.get("custIndustry");
                //构造查询方式
                Predicate predicate = cb.equal(custName, "马云");  //第一个参数是上一步获取的Path对象，第二个是参数的值
                Predicate predicate1 = cb.equal(custIndustry, "销售");
                //将多个查询条件组合起来(与|或)
                Predicate predicateCombo = cb.and(predicate, predicate1);

                return predicateCombo;
            }
        };
        Customer customer = customerDao.findOne(specification);
        System.out.println("customer = " + customer);
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 根据客户名称进行模糊查询
     * @Date 21:47 2019/8/13
     * @Param []
     * @return void
     *
     * cb.like lt,ge,le,gt等
     *  不同于cb.equal方法，不能直接使用Path对象，需要指定Path对象的参数类型，再使用
     **/
    @Test
    public void testFindLike(){
        Specification<Customer> specification = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //获取查询条件
                Path<Object> custName = root.get("custName");
                //构造查询方式
                Predicate predicate = cb.like(custName.as(String.class), "%张%");

                return predicate;
            }
        };
        List<Customer> customerList = customerDao.findAll(specification);
        for (Customer customer : customerList) {
            System.out.println("customer = " + customer);
        }
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 排序查询
     * @Date 21:57 2019/8/13
     * @Param []
     * @return void
     **/
    @Test
    public void testFindSort(){
        Specification<Customer> specification = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //获取查询条件
                Path<Object> custName = root.get("custName");
                //构造查询方式
                Predicate predicate = cb.like(custName.as(String.class), "%张%");

                return predicate;
            }
        };
        //创建排序对象
        //第一个参数，排序的顺序（正序、倒序）
        //第二个参数，排序的属性名称
        Sort sort = new Sort(Sort.Direction.DESC,"custId");

        List<Customer> customerList = customerDao.findAll(specification,sort);
        for (Customer customer : customerList) {
            System.out.println("customer = " + customer);
        }
    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 测试分页查询
     * @Date 22:02 2019/8/13
     * @Param []
     * @return void
     **/
    @Test
    public void testFindPage(){

        Specification<Customer> specification = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //获取查询条件
                Path<Object> custName = root.get("custName");
                //构造查询方式
                Predicate predicate = cb.like(custName.as(String.class), "%张%");

                return predicate;
            }
        };

        /*
         * 创建分页对象
         * PageRequest是Pageable的实现类
         * 构造方法需要啷个参数
         *  1.当前查询的页数  ，从0开始
         *  2.每页查询的数量
         **/
        Pageable pageable = new PageRequest(0,3);
        //执行查询
        Page<Customer> customers = customerDao.findAll(specification, pageable);

        List<Customer> customerList = customers.getContent();
        for (Customer customer : customerList) {
            System.out.println("customer = " + customer);
        }

        System.out.println("总条数:"+customers.getTotalElements());
        System.out.println("总页数:"+customers.getTotalPages());
    }

}