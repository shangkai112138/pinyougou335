package cn.itcast.core.service.address;

import cn.itcast.core.pojo.address.Cities;

import java.util.List;

public interface CitiesService {
    List findAll();

    /**
     * 根据省级id查询市级
     * @param provinceId
     * @return
     */
    List findByProvinceId(String provinceId);
}
