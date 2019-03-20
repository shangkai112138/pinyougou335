package cn.itcast.core.service.address;

import cn.itcast.core.pojo.address.Areas;

import java.util.List;

public interface AreasService {
    List findAll();

    /**
     * 根据市级id查询县级
     * @param cityId
     * @return
     */
    List findByCityId(String cityId);
}
