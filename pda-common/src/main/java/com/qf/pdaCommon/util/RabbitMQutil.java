package com.qf.pdaCommon.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author ：lh
 * @date ：Created in 2022/3/14 13:41
 */
public class RabbitMQutil {
    private static ConnectionFactory factory = new ConnectionFactory();

    static {
        //ip
        factory.setHost("localhost");
        //端口号
        factory.setPort(5672);
        //账号
        factory.setUsername("guest");
        //密码

        factory.setPassword("guest");

    }

    public static Connection getConnection() {
    Connection connection=null;

        try {
            connection=factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
         return connection;
    }


}
