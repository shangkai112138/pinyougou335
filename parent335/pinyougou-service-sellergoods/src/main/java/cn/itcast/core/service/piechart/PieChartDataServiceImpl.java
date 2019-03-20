package cn.itcast.core.service.piechart;

import cn.itcast.core.dao.order.OrderDao;
import com.alibaba.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PieChartDataServiceImpl implements PieChartDataService {

    @Resource
    private OrderDao orderDao;

    //饼状图显示数据
    @Override
    public List PieChartData() {
        return  orderDao.selectPieChartData();
    }

}
