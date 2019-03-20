package cn.itcast.core.service.order;

import cn.itcast.core.entity.PageResult;
import cn.itcast.core.pojo.order.Order;

import java.util.List;

public interface OrderService {

    /**
     * 提交订单
     * @param order
     */
    public void add(String username, Order order);

    public List<Order> findAll();

    public PageResult findPage(Order order, Integer page, Integer rows);

    public Order findById(Long orderId);

    /**
     * 分页显示
     * @param page
     * @param rows
     * @return
     */
    PageResult findByPage(Integer page, Integer rows);
}
