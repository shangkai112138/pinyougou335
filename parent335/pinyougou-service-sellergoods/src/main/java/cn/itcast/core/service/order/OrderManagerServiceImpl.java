package cn.itcast.core.service.order;

import cn.itcast.core.dao.order.OrderDao;
import cn.itcast.core.pojo.order.Order;
import com.alibaba.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderManagerServiceImpl implements OrderManagerService{

    @Resource
    OrderDao orderDao;

    /**
     * 运营商查询所有订单
     * @return
     */
    @Override
    public List<Order> findAll() {
        //获取数据
        List<Order> list = orderDao.selectByExample(null);
        return list;
    }
}
