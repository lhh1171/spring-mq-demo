package com.springcloud.kafka_listener.service;

import com.springcloud.kafka_listener.model.OrderDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;
import java.util.Date;


@Component
public class OrderManager {
    /*
    //此时这两个注解的连用就类似 @Resource(name="testClass1")
    @Autowired
    @Qualifier("testClass1")
    */
    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;
//    @Autowired
//    @Qualifier("springEventPublishService")
//    private EventPublishService eventPublishService;

    /**
     * 模拟创建订单
     */
    public void createOrder(String userId) {
        // 1、生成订单号
        long orderId = 12345L;

        OrderDTO order = new OrderDTO();
        order.setOrderId(orderId);
        order.setCreateTime(new Date());

        // 2、调用第三方的接口, 去通知订单创建
        // invoke service...

        // 使用事件发布机制解耦
        //eventPublishService.publishEvent(new OrderCreateEvent(this, order));

        //或者发布到kafka,使用kafkaProducer发布，consumer里去写逻辑业务

    }

    /**
     * 模拟订单支付
     */
    public void payOrder() {
        // 1、生成订单号
        long orderId = 12345L;

        OrderDTO order = new OrderDTO();
        order.setOrderId(orderId);
        order.setCreateTime(new Date());



        //或者发布到kafka,使用kafkaProducer发布，consumer里去写逻辑业务
        kafkaTemplate.send("payOrder","2",order).addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("发送失败");
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                System.out.println("发送成功");
            }
        });


    }
}
