package cn.itcast.core.service.order;

import cn.itcast.core.entity.PageResult;
import cn.itcast.core.pojo.order.Order;

import java.util.List;

public interface OrderService {

    public void add(String username, Order order);

    public List<Order> findAll();

    public PageResult findPage(Order order, Integer page, Integer rows);

    public Order findById(Long orderId);
}
