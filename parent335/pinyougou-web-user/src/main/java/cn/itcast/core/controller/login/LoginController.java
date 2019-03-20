package cn.itcast.core.controller.login;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/showName.do")
    public String loginName(){

        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        if (name == null && name.equals("anonymousUser")){
            return "该用户没有登录";
        }

        return name;
    }
}
