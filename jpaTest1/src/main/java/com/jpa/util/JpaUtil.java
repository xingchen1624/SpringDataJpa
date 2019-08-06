/**
 * Copyright (C), 2012-2019, by xavier chen
 * FileName: JpaUtil
 * Author:   chen
 * Date:     2019/8/6 21:22
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jpa.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 〈一句话功能简述〉<br> 
 * 〈创建一个工具类，用来初始化实体管理器的工厂类并生成jpa的实体管理器，减少性能损耗
 * 不需要每次都new〉
 *
 * @author chen
 * @createDate 2019/8/6
 * @since 1.0.0
 */
public final class JpaUtil {
    private static EntityManagerFactory entityManagerFactory;

    static {
        //1.加载配置文件创建工厂（实体管理类工厂）
        entityManagerFactory = Persistence.createEntityManagerFactory("myJpa");
    }


    /**
     * 获取EntityManager对象
     * @return EntityManager
     */
    public static EntityManager getEntityManager (){
        return entityManagerFactory.createEntityManager();
    }
}