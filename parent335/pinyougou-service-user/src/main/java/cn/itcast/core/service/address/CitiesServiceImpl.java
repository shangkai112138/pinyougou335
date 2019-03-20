package cn.itcast.core.service.address;

import cn.itcast.core.dao.address.CitiesDao;
import cn.itcast.core.pojo.address.CitiesQuery;
import cn.itcast.core.service.address.CitiesService;
import com.alibaba.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CitiesServiceImpl implements CitiesService {

    @Resource
    private CitiesDao citiesDao;

    @Override
    public List findAll() {
        return citiesDao.selectByExample(null);
    }

    /**
     * 根据省级id查询市级
     * @param provinceId
     * @return
     */
    @Override
    public List findByProvinceId(String provinceId) {
        if(provinceId != null){
            CitiesQuery citiesQuery = new CitiesQuery();
            citiesQuery.createCriteria().andProvinceidEqualTo(provinceId);
            return citiesDao.selectByExample(citiesQuery);
        }
        return null;
    }
}
