package cn.itcast.core.controller.brand;


import cn.itcast.core.service.brand.BrandManagerService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/brand")
public class BrandManager {
    @Reference
    BrandManagerService brandManagerService;
    @ResponseBody
    @RequestMapping(value="uploadExcel.do",method={RequestMethod.GET, RequestMethod.POST})
    public String uploadExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {

        return brandManagerService.uploadExcel(request,response);
    }
}