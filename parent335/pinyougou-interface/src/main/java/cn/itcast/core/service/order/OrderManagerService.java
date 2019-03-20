package cn.itcast.core.service.order;

import cn.itcast.core.pojo.order.Order;

import java.util.List;

public interface OrderManagerService {

    /**
     * 运营商查询所有订单
     * @return
     */
    List<Order> findAll();

}
