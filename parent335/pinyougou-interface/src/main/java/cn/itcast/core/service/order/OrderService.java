package cn.itcast.core.service.order;

import cn.itcast.core.entity.PageResult;
import cn.itcast.core.pojo.order.Order;

public interface OrderService {

    /**
     * 提交订单
     * @param order
     */
    public void add(String username, Order order);

    /**
     * 分页显示
     * @param page
     * @param rows
     * @return
     */
    PageResult findByPage(Integer page, Integer rows);
}
