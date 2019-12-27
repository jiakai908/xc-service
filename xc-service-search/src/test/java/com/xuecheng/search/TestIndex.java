package com.xuecheng.search;

import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述:  <br>
 *
 * @author jkk
 * @date 2019年12月27
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestIndex {

    @Autowired
    RestHighLevelClient client;

    @Autowired
    RestClient restClient;

    //创建索引
    @Test
    public void testCreateIndex() throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("xc_course");
        //设置参数
        createIndexRequest.settings(Settings.builder().put("number_of_shards","1").put("number_of_replicas","0"));
        //指定映射
        createIndexRequest.mapping("doc","{\n" +
                "         \"properties\" : {\n" +
                "            \"charge\" : {\n" +
                "               \"type\" : \"keyword\"\n" +
                "            },\n" +
                "            \"description\" : {\n" +
                "               \"analyzer\" : \"ik_max_word\",\n" +
                "               \"search_analyzer\" : \"ik_smart\",\n" +
                "               \"type\" : \"text\"\n" +
                "            },\n" +
                "            \"end_time\" : {\n" +
                "               \"format\" : \"yyyy-MM-dd HH:mm:ss\",\n" +
                "               \"type\" : \"date\"\n" +
                "            },\n" +
                "            \"expires\" : {\n" +
                "               \"format\" : \"yyyy-MM-dd HH:mm:ss\",\n" +
                "               \"type\" : \"date\"\n" +
                "            },\n" +
                "            \"grade\" : {\n" +
                "               \"type\" : \"keyword\"\n" +
                "            },\n" +
                "            \"id\" : {\n" +
                "               \"type\" : \"keyword\"\n" +
                "            },\n" +
                "            \"mt\" : {\n" +
                "               \"type\" : \"keyword\"\n" +
                "            },\n" +
                "            \"name\" : {\n" +
                "               \"analyzer\" : \"ik_max_word\",\n" +
                "               \"search_analyzer\" : \"ik_smart\",\n" +
                "               \"type\" : \"text\"\n" +
                "            },\n" +
                "            \"pic\" : {\n" +
                "               \"index\" : false,\n" +
                "               \"type\" : \"keyword\"\n" +
                "            },\n" +
                "            \"price\" : {\n" +
                "               \"type\" : \"float\"\n" +
                "            },\n" +
                "            \"price_old\" : {\n" +
                "               \"type\" : \"float\"\n" +
                "            },\n" +
                "            \"pub_time\" : {\n" +
                "               \"format\" : \"yyyy-MM-dd HH:mm:ss\",\n" +
                "               \"type\" : \"date\"\n" +
                "            },\n" +
                "            \"qq\" : {\n" +
                "               \"index\" : false,\n" +
                "               \"type\" : \"keyword\"\n" +
                "            },\n" +
                "            \"st\" : {\n" +
                "               \"type\" : \"keyword\"\n" +
                "            },\n" +
                "            \"start_time\" : {\n" +
                "               \"format\" : \"yyyy-MM-dd HH:mm:ss\",\n" +
                "               \"type\" : \"date\"\n" +
                "            },\n" +
                "            \"status\" : {\n" +
                "               \"type\" : \"keyword\"\n" +
                "            },\n" +
                "            \"studymodel\" : {\n" +
                "               \"type\" : \"keyword\"\n" +
                "            },\n" +
                "            \"teachmode\" : {\n" +
                "               \"type\" : \"keyword\"\n" +
                "            },\n" +
                "            \"teachplan\" : {\n" +
                "               \"analyzer\" : \"ik_max_word\",\n" +
                "               \"search_analyzer\" : \"ik_smart\",\n" +
                "               \"type\" : \"text\"\n" +
                "            },\n" +
                "            \"users\" : {\n" +
                "               \"index\" : false,\n" +
                "               \"type\" : \"text\"\n" +
                "            },\n" +
                "            \"valid\" : {\n" +
                "               \"type\" : \"keyword\"\n" +
                "            }\n" +
                "         }\n" +
                "      }", XContentType.JSON);

        //操作索引的客户端
        IndicesClient indices = client.indices();
        //执行创建索引库
        CreateIndexResponse createIndexResponse = indices.create(createIndexRequest);
        boolean acknowledged = createIndexResponse.isAcknowledged();
        System.out.println(acknowledged);
    }

    //删除索引库
    @Test
    public void testDeleteIndex() throws IOException {
        //删除索引对象
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("xc_course");
        //操作索引客户端
        IndicesClient indices = client.indices();
        //执行删除索引
        DeleteIndexResponse deleteIndexResponse = indices.delete(deleteIndexRequest);
        //得到响应
        boolean acknowledged = deleteIndexResponse.isAcknowledged();
        System.out.println(acknowledged);
    }

    //添加文档
    @Test
    public void testAddDoc() throws IOException {
        //准备json数据
        Map<String,Object> jsonMap = new HashMap<>();
        jsonMap.put("name","Bootstrap开发");
        jsonMap.put("description","Bootstrap是由Twitter 推出的一个前台页面开发框架，是一个非常流行的开发框架，此框架集成了多种页面效果。此开发框架包含了大量 的CSS、JS程序代码，可以帮助开发者（尤其是不擅长页面开发的程序人员）轻松的实现一个不受浏览器限制的精 美界面效果。");
        jsonMap.put("studymodel", "201002");
        jsonMap.put("price",38.6f);
        jsonMap.put("timestamp", "2018-04-25 19:11:35");
        jsonMap.put("pic", "group1/M00/00/00/wKhlQFs6RCeAY0pHAA Jx5ZjNDEM428.jpg");
        //创建索引对象
        IndexRequest indexRequest = new IndexRequest("xc_course", "doc");
        indexRequest.source(jsonMap);
        //通过client进行http请求
        IndexResponse indexResponse = client.index(indexRequest);
        DocWriteResponse.Result result = indexResponse.getResult();
        System.out.println(result);

    }

    //查询文档
    @Test
    public void testGetDoc() throws IOException {
        GetRequest getRequest = new GetRequest("xc_course", "doc", "Az5URm8BDM2HYUmCFb7G");
        GetResponse getResponse = client.get(getRequest);
        //得到文档内容
        Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
        System.out.println(sourceAsMap);
    }
}
