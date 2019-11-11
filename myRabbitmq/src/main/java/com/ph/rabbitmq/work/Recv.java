package com.ph.rabbitmq.work;

import com.ph.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

public class Recv {

    private final static String QUEUE_NAME = "test_queue_work";

    public static void main(String[] args) throws IOException, InterruptedException {
        //获取mq连接和通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //同一时刻服务器只会发一条消息给消费者
        channel.basicQos(1);

        //定义队列的消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        //监听队列，true表示自动返回完成状态，false表示手动返回完成状态
        channel.basicConsume(QUEUE_NAME,false,queueingConsumer);

        while(true){
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String message =new String(delivery.getBody());
            System.out.println(" [x] Received '" + message + "'");
            //休眠一下，相当于消费这个消息使用了这些时间
            Thread.sleep(10);

            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);

        }

    }
}
