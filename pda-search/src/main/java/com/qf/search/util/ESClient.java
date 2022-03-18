package com.qf.search.util;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * @author ：lh
 * @date ：Created in 2022/3/18 13:29
 */
public class ESClient {
//    获取ES服务链接的方法
    public static RestHighLevelClient getClient(){
        //请求对象 es服务器
        HttpHost httpHost=new HttpHost("127.0.0.1",9200);
        //创建RestClientBuilder对象
        RestClientBuilder restClientBuilder = RestClient.builder(httpHost);
         //创建 RestHighLevelClient对象
        RestHighLevelClient client = new RestHighLevelClient(restClientBuilder);
        return client;

    }
}
