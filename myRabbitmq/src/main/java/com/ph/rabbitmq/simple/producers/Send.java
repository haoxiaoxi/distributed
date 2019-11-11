package com.ph.rabbitmq.simple.producers;

import com.ph.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class Send {
    private final static String QUEUE_NAME = "q_test_01";
    public static void main(String[] args) throws IOException {
        //获取mq连接
        Connection connection = ConnectionUtil.getConnection();
        //从连接中获取到通道
        Channel channel = connection.createChannel();
        //创建队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //消息内容
        String message = "Hell World";
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        //关闭通道和连接
        channel.close();
        connection.close();
    }
}
