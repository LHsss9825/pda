package com.qf.publisher.service;

import com.qf.pdaCommon.util.RabbitMQutil;
import com.rabbitmq.client.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author ：lh
 * @date ：Created in 2022/3/14 14:08
 */
@Service
public class PublishServiceImpl implements PublishService {
//    ---------------hello,world----------------------------------------
    @Override
    public String saveTo(String name) {
        //把消息写入到交换机中
        //helloworld通信方式：一个生产者、消费者、一个队列
        //1获取连接
        Connection connection = RabbitMQutil.getConnection();
        //创建管道
        Channel channel = null;
        String result = "";

        try {

            //开启消息确认机制=======confirm（最常用的）
            channel.confirmSelect();

            channel = connection.createChannel();
            //把消息通过管道，写到交换机中，交换机进而写到管道里
            //参数1：写到哪个交换机？，交换机的名字，“”默认的交换机
            //队列的名字，也是路由的规则
            //携带的附加消息
            //具体写入交换机的消息，bytes类型

            channel.basicPublish("", "java2104queue", null, name.getBytes());


            try {
                if (channel.waitForConfirms()) {
                    result = "写入消息队列成功！";
                } else {
                    result = "写入交换机失败";
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            如果一次性写入多个消息(批量confirm方式)
            String meg = "hello";
//            开启消息确认机制
            for (int i = 0; i < 10; i++) {

                meg += i;
                channel.basicPublish("", "java210401", null, meg.getBytes());
            }
//            如果批量成功则通过，如果有一个失败，则全部不写入，同时抛出异常，自动回滚
            try {
                channel.waitForConfirmsOrDie();
                result = "批量写入交换机成功";
            } catch (InterruptedException e) {
                e.printStackTrace();
                result = "批量写入交换机失败";
            }

//          异步confirm确认方式--批量写入，再写入过程中，会具体显示具体哪个消息失败，也会进行回滚
//            主要是用于日志
//          开启消息确认
            channel.confirmSelect();
            String meg2 = "world";
            for (int i = 0; i < 1000; i++) {
                meg2 = meg2 + i;
                channel.basicPublish("", "java2104002", null, meg2.getBytes());
            }
//添加监听，设置回调，当某个消息写入交换机失败则进行回调
            channel.addConfirmListener(new ConfirmListener() {
                @Override
//写入交换机成功是回调（一般不用）
                public void handleAck(long l, boolean b) throws IOException {
                    System.out.println("消息写入成功！！！！！标识：" + l + ",是否为批量：" + b);
                }

                @Override
                public void handleNack(long l, boolean b) throws IOException {
//消息写入失败是回调此方法
                    System.out.println("消息" + l + "此消息写入交换机失败");
                }
            });

//            confirm只能确保消息写入交换机中
//使用return机制 确保消息由交换机写进队列（这是由运维人员做的事情）
//采用return监听机制：监听交换机中的消息是否成功写入队列中，如果那个消息没有写入就会进行回调，并写入日志里面，有运维人员查看日志
            channel.addReturnListener(new ReturnListener() {
                @Override
                public void handleReturn(int i, String s, String s1, String s2, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
                    //当交换机中的消息吗，没有成功写入队列则会回调此方法
                    //参数bytes就是没有被写入到队列的那个消息
                    // 解决方法1  我们可以将他进行日志记录，或手动调用程序直接消费
                    String meg = new String(bytes, "utf-8");
                    System.out.println(meg + "没有写入消息队列");
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
            result = "写入消息队列失败！";
        } finally {
            //释放资源
            try {
                channel.close();
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    //    -------------------------广播模式-------------------------------------------
    @Override
    public String saveToMqFanout(String name) {
        Connection connection = RabbitMQutil.getConnection();
        Channel channel = null;
        String reslut = "";
        try {
            channel = connection.createChannel();
//            通过管道对交换机进行i定义
//            参数1：交换机名
//            参数1：交换机类型
            channel.exchangeDeclare("pubsub-exchange", BuiltinExchangeType.FANOUT);
//通过管道对队列进行定义
//            参数一：队列名字
//            参数二：
            channel.queueBind("java2104queue1", "pubsub-exchange", "");
            channel.queueBind("java2104queue2", "pubsub-exchange", "");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                channel.close();
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
//-----------------------路由，模式-------------------------------------------------------------

    @Override
    public String saveToMyByRouting(String name, String addr, String phone) {
        Connection connection = RabbitMQutil.getConnection();
        Channel channel = null;
        try {
            channel = connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String meg = "";

        try {
            //定义交换机
            //参数1：交换机的名字
            //交换机的类型：DIRECT   路由类型：根据路由规则写入某个符合路由规则的队列
            channel.exchangeDeclare("routing-exchange", BuiltinExchangeType.DIRECT);
            //绑定队列： 把队列和交换机绑定在一起
            //参数1：队列的名字
            //参数2：交换机的名字
            //参数3：路由规则
            channel.queueBind("routing-queue-name", "routing-exchange", "NAME");
            channel.queueBind("routing-queue-addr", "routing-exchange", "ADDR");
            // channel.queueBind("routing-queue-phone","routing-exchange","PHONE");

            //把消息通过管道写入交换机中
            //参数1：交换机的名字，把消息写到指定名称的交换机中
            //参数2：路由规则  ，如果没有路由规则，就是队列的名字
            channel.basicPublish("routing-exchange", "NAME", null, name.getBytes());
            channel.basicPublish("routing-exchange", "ADDR", null, addr.getBytes());
            // channel.basicPublish("routing-exchange","PHONE",null,phone.getBytes());
            meg = "success";
        } catch (IOException e) {
            e.printStackTrace();
            meg = "failed";
        } finally {
            try {
                channel.close();
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }


        return meg;
    }

//----------------------------------------Topic模式--------------------------------------
    @Override
    public String saveToMqByTopic(String s1, String s2) {
        Connection connection = RabbitMQutil.getConnection();
        Channel channel = null;
        String meg = "";
        try {
            channel = connection.createChannel();
            //声明交换机的类型
            channel.exchangeDeclare("topic-exchange", BuiltinExchangeType.TOPIC);
            //绑定队列
            channel.queueBind("javaqueue", "topic-exchange", "*.java.*");
            channel.queueBind("pythonqueue", "topic-exchange", "python.#");

            //把消息通过管道写到交换机中
            channel.basicPublish("topic-exchange", "se.java.ee", null, s1.getBytes());
            channel.basicPublish("topic-exchange", "python.flask.diango", null, s2.getBytes());
            meg = "success";
        } catch (IOException e) {
            e.printStackTrace();
            meg = "failed";
        } finally {
            try {
                channel.close();
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
        return meg;
    }
}
