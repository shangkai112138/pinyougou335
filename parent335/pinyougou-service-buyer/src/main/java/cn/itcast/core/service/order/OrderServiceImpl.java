package cn.itcast.core.service.order;

import cn.itcast.core.dao.item.ItemDao;
import cn.itcast.core.dao.order.OrderDao;
import cn.itcast.core.dao.order.OrderItemDao;
import cn.itcast.core.entity.PageResult;
import cn.itcast.core.pojo.cart.Cart;
import cn.itcast.core.pojo.item.Item;
import cn.itcast.core.pojo.order.Order;
import cn.itcast.core.pojo.order.OrderItem;
import cn.itcast.core.pojo.order.OrderQuery;
import cn.itcast.core.utils.uniquekey.IdWorker;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private OrderItemDao orderItemDao;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private IdWorker idWorker;

    @Resource
    private ItemDao itemDao;

    /**
     * 提交订单
     * @param username
     * @param order
     */
    @Override
    public void add(String username, Order order) {
        // 保存订单：已商家为单位（购物车也是以商家为单位）
        List<Cart> cartList = (List<Cart>) redisTemplate.boundHashOps("BUYER_CART").get(username);
        if(cartList != null && cartList.size() > 0){
            for (Cart cart : cartList) {
                double payment = 0;
                long orderId = idWorker.nextId();
                order.setOrderId(orderId);  // 订单主键
                // 订单金额该商家下所有的订单金额
                order.setPaymentType("1");  // 支付类型：在线支付
                order.setStatus("1");       // 订单状态：未付款
                order.setCreateTime(new Date());
                order.setUpdateTime(new Date());
                order.setUserId(username);  // 下单用户
                order.setSourceType("2");   // 订单来源：pc端
                order.setSellerId(cart.getSellerId());  // 商家id

                // 保存订单明细
                List<OrderItem> orderItemList = cart.getOrderItemList();
                if(orderItemList != null && orderItemList.size() > 0){
                    for (OrderItem orderItem : orderItemList) {
                        long id = idWorker.nextId();
                        orderItem.setId(id);
                        Item item = itemDao.selectByPrimaryKey(orderItem.getItemId());
                        orderItem.setGoodsId(item.getGoodsId());    // 商品id
                        orderItem.setOrderId(orderId);              // 订单id
                        orderItem.setTitle(item.getTitle());        // 标题
                        orderItem.setPrice(item.getPrice());        // 单价
                        double totalFee = item.getPrice().doubleValue() * orderItem.getNum();
                        payment += totalFee;
                        orderItem.setTotalFee(new BigDecimal(totalFee));            // 小计
                        orderItem.setPicPath(item.getImage());      // 商品图片
                        orderItem.setSellerId(item.getSellerId());  // 商家id
                        orderItemDao.insertSelective(orderItem);
                    }
                }
                // 设置该商家下的总金额
                order.setPayment(new BigDecimal(payment));
                orderDao.insertSelective(order);
            }
        }
        // 删除购物车
        redisTemplate.boundHashOps("BUYER_CART").delete(username);
    }

    @Override
    public List<Order> findAll() {
        List<Order> orderList = orderDao.selectByExample(null);
        return orderList;
    }


    /**
     * 条件查询加分页,如果没有条件,查询所有订单
     * @param order
     * @param page
     * @param rows
     * @return
     */
    @Override
    public PageResult findPage(Order order, Integer page, Integer rows) {
        //利用分页助手
        PageHelper.startPage(page,rows);
        OrderQuery query = new OrderQuery();
        OrderQuery.Criteria criteria = query.createCriteria();
        if (order != null){
            if (order.getOrderId() != null && !"".equals(order.getOrderId())){
                criteria.andOrderIdEqualTo(order.getOrderId());
            }
        }
        Page<Order> orderList =(Page<Order>) orderDao.selectByExample(query);
        return new PageResult(orderList.getTotal(), orderList.getResult());
    }

    @Override
    public Order findById(Long orderId) {
        Order order = orderDao.selectByPrimaryKey(orderId);
        return order;
    }
}
