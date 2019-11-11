package com.ph.rabbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

public class ConnectionUtil {

    public static Connection getConnection() throws IOException {
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost("localhost");
        //设置端口号
        factory.setPort(5672);
        //设置账号信息 账号，密码，vhost
        factory.setUsername("admin");
        factory.setPassword("123456");
        factory.setVirtualHost("testhost");
        Connection connection = factory.newConnection();
        return connection;
    }
}
