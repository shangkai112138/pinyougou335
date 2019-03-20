package cn.itcast.core.controller.address;

import cn.itcast.core.pojo.address.Provinces;
import cn.itcast.core.service.address.ProvincesService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/province")
public class ProvinceController {

    @Reference
    private ProvincesService provinceService;

    /**
     * 查询所有省
     * @return
     */
    @RequestMapping("/findAll.do")
    public List<Provinces> findAll(){
        return provinceService.findAll();
    }

}
