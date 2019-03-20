package cn.itcast.core.controller.order;
import cn.itcast.core.dao.good.GoodsDao;
import cn.itcast.core.pojo.good.Goods;
import cn.itcast.core.pojo.order.OrderItem;
import cn.itcast.core.service.order.OrderItemService;
import cn.itcast.core.vo.PieVo;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pie")
public class OrderItemController {

    @Reference
    private OrderItemService orderItemService;

    @Reference
    private GoodsDao goodsDao;


    @RequestMapping("/findTotalMoneyGroupByGoodsIdForSellId.do")
    public PieVo findTotalMoneyGroupByGoodsIdForSellId(){
        String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();

        PieVo pieVo = new PieVo();

        List<Map> data  = orderItemService.findTotalMoneyGroupByGoodsIdForSellId(sellerId);

        List<Map> seriesData  = new ArrayList<Map>();
        List<String> legendData = new ArrayList<>();
        Integer[] selected = new Integer[legendData.size()];

        for (int i = 0 ; i < data.size() ; i++) {
            Map map = data.get(i);
            Map<String, Object> mapTemp = new HashMap<>();
            Long goodsId = (Long) map.get("goodsId");
            Double totalFee = (Double) map.get("totalFee");
            Goods goods = goodsDao.selectByPrimaryKey(goodsId);

            legendData.add(goods.getGoodsName());

            selected[i] = i ;

            mapTemp.put("name",goods.getGoodsName());
            mapTemp.put("value",totalFee);
            seriesData.add(mapTemp);
        }

        System.out.println(legendData);
        System.out.println(selected);
        System.out.println(seriesData);

        pieVo.setLegendData(legendData);
        pieVo.setSelected(selected);
        pieVo.setSeriesData(seriesData);
        return pieVo;
    }

}
