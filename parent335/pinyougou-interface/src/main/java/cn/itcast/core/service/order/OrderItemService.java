package cn.itcast.core.service.order;

import java.util.List;
import java.util.Map;

public interface OrderItemService {
    List<Map> findTotalMoneyGroupByGoodsIdForSellId(String sellerId);
}
