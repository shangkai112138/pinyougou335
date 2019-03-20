package cn.itcast.core.service.goods;

import cn.itcast.core.pojo.good.Goods;
import java.util.List;

public interface GoodsManagerService {
    List<Goods> findAll();
}
