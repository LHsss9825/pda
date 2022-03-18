package com.qf.getapplyfromqueue.service;

import com.qf.pdaCommon.util.RabbitMQutil;
import com.rabbitmq.client.*;
import org.springframework.stereotype.Service;

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
        Connection connection= RabbitMQutil.getConnection();
        //2、创建管道
        Channel channel=null;
        try {
            channel=connection.createChannel();
            //3、声明/定义队列
            //参数1：队列的名字
            //参数2：队列里的消息是否持久化,true为持久化，
            //参数3：是否被多个消费者消费 false 被一个消费者消费
            //参数4：是否自动删除消息---消费者宕机后，造成无消费者时，不删除，堆积
            //参数5：是否有其他额外的消息，null   无
            channel.queueDeclare("javaqueue",true,false,false,null);

            //创建监听,启动后一直监听指定队列里的消息
            //如果队列里有消息，就自动调用handleDelivery，来消费---获取队列里的消息
            //如果队列里没有消息，则等待
            DefaultConsumer consumer=new DefaultConsumer(channel){
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    //调用dao 把body里的数据写入数据
                    String strMeg=new String(body);
                    System.out.println("从消息队列中获取到消息："+strMeg);
                }
            };
            //自动消息确认机制：当消息队列得知消息被取出后，自动删除该消息
            channel.basicConsume("javaqueue",true,consumer);
            System.out.println("消费者1开始监听队列……");
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
