package com.qf.getaaplyfromqueue2.util;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisUtil {
    //jedis连接池
    private static JedisPool jedisPool=null;
    //连接池的配置信息
    static {
        //创建连接池的配置类
        GenericObjectPoolConfig poolConfig=new GenericObjectPoolConfig();
        //连接池中最大活跃连接数量
        poolConfig.setMaxTotal(100);
        //设置空闲数量
        poolConfig.setMaxIdle(10);
        //设置等待超时时间
        poolConfig.setMaxWaitMillis(3000);
        //创建连接池对象
        jedisPool=new JedisPool(poolConfig,"127.0.0.1",6379);

    }
    //获取连接方法
    public static Jedis getJedis(){
        //返回： 从连接池中获取的连接
        return jedisPool.getResource();
    }
    //关闭连接方法
    public static void colseJedis(Jedis jedis){
        jedis.close();
    }
}
