package cn.itcast.core.controller.address;

import cn.itcast.core.pojo.address.Cities;
import cn.itcast.core.service.address.CitiesService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    @Reference
    private CitiesService citiesService;

    /**
     * 根据省级id查询市级
     * @param provinceId
     * @return
     */
    @RequestMapping("/findByProvinceId.do")
    public List<Cities> findByProvinceId(String provinceId){
        return  citiesService.findByProvinceId(provinceId);
    }
}
