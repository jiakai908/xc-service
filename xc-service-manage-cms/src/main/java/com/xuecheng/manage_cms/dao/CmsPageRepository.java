package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @program: xc-service
 * @description: ${description}
 * @author: jkk
 * @create: 2019-12-22 10:50
 */

public interface CmsPageRepository extends MongoRepository<CmsPage,String> {

}
