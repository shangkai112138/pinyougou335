package cn.itcast.core.service.order;
import cn.itcast.core.dao.order.OrderItemDao;
import com.alibaba.dubbo.config.annotation.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class OrderItemServiceImpl implements OrderItemService{

    @Resource
    private OrderItemDao orderItemDao;

    @Override
    public List<Map> findTotalMoneyGroupByGoodsIdForSellId(String sellerId) {
        return  orderItemDao.findTotalMoneyGroupByGoodsIdForSellId(sellerId);
    }
}
