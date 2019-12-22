package com.xuecheng.framework.domain.cms.request;

import com.xuecheng.framework.model.request.RequestData;
import lombok.Data;

/**
 * @program: xc-service
 * @description: ${description}
 * @author: jkk
 * @create: 2019-12-21 23:16
 */
@Data
public class QueryPageRequest extends RequestData {
    //站点id
    private String siteId;
    //页面Id
    private String pageId;
    //页面名称
    private String pageName;
    //别名
    private String pageAliase;
    //模版Id
    private String templateId;
}
