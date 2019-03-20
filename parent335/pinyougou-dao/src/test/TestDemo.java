import cn.itcast.core.dao.order.OrderDao;
import cn.itcast.core.dao.order.OrderItemDao;
import cn.itcast.core.pojo.order.Order;
import cn.itcast.core.pojo.order.OrderItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;
import java.util.Map;

//spring 和 junit 整合读取注解文件
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/*.xml"})
public class TestDemo {
    @Autowired
    OrderDao orderDao;

    @Autowired
    OrderItemDao orderItemDao;
    @Test
    public void test(){
//        List<Order> orderList = orderDao.selectByExample(null);
//        System.out.println(orderList);
        List<OrderItem> orderItems = orderItemDao.selectByExample(null);
        System.out.println(orderItems);
        System.out.println("----------");

        List qiandu = orderItemDao.findTotalMoneyGroupByGoodsIdForSellId("qiandu");

        System.out.println(qiandu);
    }
}
