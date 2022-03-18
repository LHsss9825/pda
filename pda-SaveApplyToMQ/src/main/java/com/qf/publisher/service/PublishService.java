package com.qf.publisher.service;

/**
 * @author ：lh
 * @date ：Created in 2022/3/14 14:04
 */
public interface PublishService {
    String saveTo(String name);
    //发布订阅模式（交换机使用广播模式）
    String saveToMqFanout(String name);
    //路由通讯模式
    String saveToMyByRouting(String name,String addr,String phone);
    //Topic通讯模式
    String saveToMqByTopic(String s1,String s2);
}
