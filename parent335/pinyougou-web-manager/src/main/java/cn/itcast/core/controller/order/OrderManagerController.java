package cn.itcast.core.controller.order;

import cn.itcast.core.entity.PageResult;
import cn.itcast.core.pojo.order.Order;
import cn.itcast.core.service.order.OrderService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orderManager")
public class OrderManagerController {

    @Reference
    private OrderService orderService;


    @RequestMapping("/findAll")
    public List<Order> findAll(){
        List<Order> list = orderService.findAll();
        return list;
    }


    /**
     * 条件查询
     * @param order
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/search.do")
    public PageResult search(@RequestBody Order order, Integer page, Integer rows) {
        PageResult result = orderService.findPage(order, page, rows);
        return result;
    }

    /**
     * 根据id查询某一个订单数据
     * @param orderId
     * @return
     */
    @RequestMapping("/findOne")
    public Order findById(Long orderId){
        Order order = orderService.findById(orderId);
        return order;
    }


}
