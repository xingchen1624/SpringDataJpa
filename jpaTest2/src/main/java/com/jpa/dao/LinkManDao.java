package com.jpa.dao;

import com.jpa.entity.LinkMan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 功能描述: <br>
 * @Author lzc
 * @Description //TODO LinkMan实体类对应的jpa的dao接口
 * @Date 20:38 2019/8/14
 * @Param 
 * @return 
 **/
public interface LinkManDao extends
        JpaRepository<LinkMan,Long>, JpaSpecificationExecutor<LinkMan> {
}
