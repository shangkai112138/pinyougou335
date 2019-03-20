package cn.itcast.core.controller.user;

import cn.itcast.core.entity.PageResult;
import cn.itcast.core.entity.Result;
import cn.itcast.core.pojo.user.User;
import cn.itcast.core.service.user.UserService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class userController {

    @Reference
    private UserService userService;

    /**
     * 查询所有用户
     * @return
     */
    @RequestMapping("/findAll.do")
    public List<User> findAll(){
        return userService.findAll();
    }

    /**
     * 分页查询
     * @param page 当前页
     * @param rows 每页展示数据条数
     * @return
     */
    @RequestMapping("/search.do")
    public PageResult search(Integer page, Integer rows,@RequestBody User user) {
        PageResult result = userService.search(page, rows,user);
        return result;
    }

    /**
     * 修改用户状态
     * @return
     */
    @RequestMapping("/updateStatus.do")
    public Result updateStatus(Long id,String status){
        try {
            userService.updateStatus(id,status);
            return new Result(true, "修改状态成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改状态失败！");
        }
    }

    /**
     * 根据ID查询用户
     * @return
     */
    @RequestMapping("/findOne.do")
    public User findOne(Long id){
        return userService.findOne(id);
    }


}
