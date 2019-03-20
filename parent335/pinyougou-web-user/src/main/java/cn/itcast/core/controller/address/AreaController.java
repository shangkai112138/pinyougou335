package cn.itcast.core.controller.address;

import cn.itcast.core.pojo.address.Areas;
import cn.itcast.core.service.address.AreasService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/area")
public class AreaController {

    @Reference
    private AreasService areasService;

    /**
     * 根据市级id查询县级
     * @param cityId
     * @return
     */
    @RequestMapping("/findByCityId.do")
    public List<Areas> findByCityId(String cityId){
        return areasService.findByCityId(cityId);
    }
}
