package com.springcloud.kafka_listener.model;

import lombok.Data;


import java.util.Date;


@Data
public class OrderDTO  {

    private long orderId;
    private Date createTime;
}
