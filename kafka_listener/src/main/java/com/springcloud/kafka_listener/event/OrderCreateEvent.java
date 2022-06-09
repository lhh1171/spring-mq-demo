package com.springcloud.kafka_listener.event;

import com.springcloud.kafka_listener.model.OrderDTO;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;


public class OrderCreateEvent extends ApplicationEvent{

    @Getter
    private OrderDTO orderDTO;

    /**
     * 重写构造函数
     *
     * @param source    发生事件的源
     * @param orderDTO  事件对应的信息
     */
    public OrderCreateEvent(Object source, OrderDTO orderDTO) {
        super(source);
        this.orderDTO = orderDTO;
    }
}
