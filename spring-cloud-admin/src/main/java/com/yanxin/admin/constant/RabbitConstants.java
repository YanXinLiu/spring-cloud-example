package com.yanxin.admin.constant;

public class RabbitConstants {

    /**
     * work 模式
     */
    public final static String WORK_QUEUE_NAME = "work_queue";

    /**
     * route模式
     */
    public final static String ROUTING_EXCHANGE_NAME = "routing_exchange";
    public final static String ROUTING_FIRST_QUEUE_NAME = "routing_first_queue";
    public final static String ROUTING_SECOND_QUEUE_NAME = "routing_second_queue";
    public final static String ROUTING_THIRD_QUEUE_NAME = "routing_third_queue";
    public final static String ROUTING_FIRST_QUEUE_ROUTING_KEY_NAME = "routing_first_queue_routing_key";
    public final static String ROUTING_SECOND_QUEUE_ROUTING_KEY_NAME = "routing_second_queue_routing_key";
    public final static String ROUTING_THIRD_QUEUE_ROUTING_KEY_NAME = "routing_third_queue_routing_key";

}
