package cn.itcast.core.service.goods;

import cn.itcast.core.dao.good.GoodsDao;
import cn.itcast.core.pojo.good.Goods;
import com.alibaba.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class GoodsManagerServiceImpl implements GoodsManagerService{
    @Resource
    GoodsDao goodsDao ;
    @Override
    public List<Goods> findAll() {
        //获取数据
        List<Goods> list = goodsDao.selectByExample(null);
        return list;
    }
}
