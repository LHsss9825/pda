package com.qf.getaaplyfromqueue2.service;

import com.qf.getaaplyfromqueue2.util.JedisUtil;
import com.qf.pdaCommon.util.RabbitMQutil;
import com.rabbitmq.client.*;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author ：lh
 * @date ：Created in 2022/3/14 15:55
 */
@Service
public class GetMessageServiceImpl implements GetMessageService {
    @Override
    public void getMegFromMq() {
        //1、获取连接
        Connection connection = RabbitMQutil.getConnection();
        //2、创建管道
        Channel channel = null;
        try {
            channel = connection.createChannel();
            //3、声明/定义队列
            //参数1：队列的名字
            //参数2：队列里的消息是否持久化,true为持久化，
            //参数3：是否被多个消费者消费 false 被一个消费者消费
            //参数4：是否自动删除消息---消费者宕机后，造成无消费者时，不删除，堆积
            //参数5：是否有其他额外的消息，null   无
            channel.queueDeclare("pythonqueue", true, false, false, null);

            //创建监听,启动后一直监听指定队列里的消息
            //如果队列里有消息，就自动调用handleDelivery，来消费---获取队列里的消息
            //如果队列里没有消息，则等待
            channel.basicQos(1);
            Channel channel1 = channel;
            DefaultConsumer consumer = new DefaultConsumer(channel1) {
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    //分布式锁 防止
                    // 获取jedis连接
                    Jedis jedis = new Jedis("127.0.0.1", 6379);
                    //  Jedis jedis = JedisUtil.getJedis();
                    String result = jedis.set("dd", "0", "NX", "EX", 60);
                    if (result != null && result.equalsIgnoreCase("OK")) {
                        //锁添加成功、可以消费消息
                        String strMeg = new String(body);
                        System.out.println("从消息队列中获取到了消息：" + strMeg);
                        //    消费成功收后
                        jedis.set("rabbitLock", "1");
                        //    在成功消费后手动确认消息ACK
                        channel1.basicAck(envelope.getDeliveryTag(), false);
                    } else {
                        //    判断锁里的值多少
                        String lock = jedis.get("rabbitLock");
                        if (lock.equalsIgnoreCase("1")) {
                            //        可以消费
                            jedis.set("rabbitLock", "0");
                            String strMeg = new String(body);
                            System.out.println("从消息队列中获取消息：" + strMeg);
                            channel1.basicAck(envelope.getDeliveryTag(), false);
                            jedis.set("rabbitLock", "1");
                        }
                    }

                    //调用dao 把body里的数据写入数据
                    String strMeg = new String(body);
                    System.out.println("从消息队列中获取到消息：" + strMeg);
                    //在成功消费了消息后，手动消息确认ACK
                    channel1.basicAck(envelope.getDeliveryTag(), false);
                }
            };
            //自动消息确认机制：当消息队列得知消息被取出后，自动删除该消息
            channel.basicConsume("pythonqueue", false, consumer);
            System.out.println("消费者2开始监听队列……");
            System.in.read();


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            try {
                channel.close();
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }

    }
}
