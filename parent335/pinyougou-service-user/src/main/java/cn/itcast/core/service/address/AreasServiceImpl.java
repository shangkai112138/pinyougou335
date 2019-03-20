package cn.itcast.core.service.address;

import cn.itcast.core.dao.address.AreasDao;
import cn.itcast.core.pojo.address.AreasQuery;
import cn.itcast.core.service.address.AreasService;
import com.alibaba.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AreasServiceImpl implements AreasService {

    @Resource
    private AreasDao areasDao;

    @Override
    public List findAll() {
        return areasDao.selectByExample(null);
    }

    /**
     * 根据市级id查询县级
     * @param cityId
     * @return
     */
    @Override
    public List findByCityId(String cityId) {
        if(cityId != null){
            AreasQuery areasQuery = new AreasQuery();
            areasQuery.createCriteria().andCityidEqualTo(cityId);
            return  areasDao.selectByExample(null);
        }
        return null;
    }
}
