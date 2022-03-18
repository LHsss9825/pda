package com.qf.search.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qf.pdaCommon.Apply;
import com.qf.pdaprovider.controller.ProviderController;
import com.qf.search.feigns.ApplyFeign;
import com.qf.search.service.ESMageService;
import com.qf.search.util.ESClient;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：lh
 * @date ：Created in 2022/3/18 13:37
 */
@Service
public class ESMageServiceImpl implements ESMageService {
    //ES服务连接的属性
    private RestHighLevelClient client= ESClient.getClient();
    //所操作的索引
    private String index="pda";
    //声明使用的类型
    private String type="apply";

    //ObjectMapper mapper=ObjectMapper();  es自带的把对象转json字符串的工具，构造方法是默认访问修饰符，所以不可访问
    ObjectMapper mapper=new ObjectMapper();  //把对象转json
    @Autowired
    private ApplyFeign applyFeign;


    @Override
    public void creatIndex() throws IOException {
        //创建索引及类型
        //1Settings创建
        Settings.Builder settings = Settings.builder();
        settings.put("number_of_shards", 3);
        settings.put("number_of_replicas", 1);

        //准备mapping映射
        XContentBuilder maapings= JsonXContent.contentBuilder();
        maapings.startObject()
                .startObject("properties")
                .startObject("applyName")
                .field("type","text")
                .field("analyzer","ik_max_word")
                .field("index",true)
                .field("store",false)
                .endObject()
                .startObject("applyTime")
                .field("type","keyword")
                .endObject()
                .startObject("inTime")
                .field("type","keyword")
                .endObject()
                .startObject("type")
                .field("type","text")
                .field("analyzer","ik_max_word")
                .field("index",true)
                .field("store",false)
                .endObject()
                .startObject("unit")
                .field("type","keyword")
                .endObject()
                .startObject("num")
                .field("type","long")
                .endObject()
                .endObject()
                .endObject();
        CreateIndexRequest request = new CreateIndexRequest(index);
        request.settings(settings);
        request.mapping(type, maapings);
//        通过连接对象和Es进行连接
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
//        输出创建结果
        System.out.println(response.toString());

    }

    @Override
    public boolean indexExists() throws IOException {
//        创建一个request请求对象
        GetIndexRequest request = new GetIndexRequest();
        request.indices(index);
//        通过链接 发出请求
        boolean result = client.indices().exists(request, RequestOptions.DEFAULT);
        return result;
    }

    @Override
    public boolean indexDelete() throws IOException {
//       创建删除索引请求对象
        DeleteIndexRequest request = new DeleteIndexRequest();

        request.indices(index);
//        通过ES服务的连接对象，发出删除请求
        AcknowledgedResponse response = client.indices().delete(request, RequestOptions.DEFAULT);

        return false;
    }

    @Override
    public String addDocument() throws IOException {
//        List<Apply> apply = applyFeign.findApply();
//        for (Apply list : apply) {
//            String json = mapper.writeValueAsString(list);
////        准备一个请求对象
////        参数1：操作的索引
////        参数2：操作的类型
////        参数3：手动指定的id
//
//            IndexRequest request = new IndexRequest(index, type, list.toString());
//            request.source(json, XContentType.JSON);
////        通过连接对象把请求发出
//            IndexResponse response = client.index(request, RequestOptions.DEFAULT);
//            return response.getResult().toString();
//        }
//        //把数据转为json
//     return "Ok";
//    }
        //调用另一个微服务得到集合
        List<Apply> list=new ArrayList<Apply>();
        Apply apply=new Apply("djx","2022-3-8","20220309","服装","集装箱",100);
        Apply apply2=new Apply("bbb","2022-3-8","20220309","服装","集装箱",100);
        Apply apply3=new Apply("ccc","2022-3-8","20220309","服装","集装箱",100);
        Apply apply4=new Apply("ddd","2022-3-8","20220309","服装","集装箱",100);
        list.add(apply);
        list.add(apply2);
        list.add(apply3);
        list.add(apply4);

        //1、把数据转为json
        String json=mapper.writeValueAsString(apply);

        //2、准备一个请求对象
        //参数1：操作的索引
        //参数2：操作的类型
        //参数3：手动指定的id
        IndexRequest request=new IndexRequest(index,type,apply.getApplyName());
        //参数1：写入的文档数据
        request.source(json, XContentType.JSON);

        //3、通过连接对象把请求发出
        IndexResponse response=client.index(request,RequestOptions.DEFAULT);
        return response.getResult().toString();
    }


}
