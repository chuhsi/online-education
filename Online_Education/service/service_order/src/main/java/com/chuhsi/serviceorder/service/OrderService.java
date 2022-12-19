package com.chuhsi.serviceorder.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chuhsi.serviceorder.entity.Order;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author atguigu
 * @since 2020-08-16
 */
public interface OrderService extends IService<Order> {

    String createOrders(String courseId, String memberId);
}
