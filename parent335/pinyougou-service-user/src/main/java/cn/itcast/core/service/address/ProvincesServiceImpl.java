package cn.itcast.core.service.address;

import cn.itcast.core.dao.address.ProvincesDao;
import cn.itcast.core.pojo.address.Provinces;
import cn.itcast.core.service.address.ProvincesService;
import com.alibaba.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProvincesServiceImpl implements ProvincesService {

    @Resource
    private ProvincesDao provincesDao;

    /**
     * 查询所有省
     * @return
     */
    @Override
    public List<Provinces> findAll() {
        List<Provinces> provinces = provincesDao.selectByExample(null);
        return provinces;
    }
}
