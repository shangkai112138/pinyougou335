package cn.itcast.core.service.user;

import cn.itcast.core.pojo.item.Item;
import cn.itcast.core.pojo.user.User;

import java.util.List;

public interface UserService {

    /**
     * 用户获取短信验证码
     * @param phone
     */
    public void sendCode(String phone);

    /**
     * 用户注册
     * @param user
     * @param smscode
     */
    public void add(User user, String smscode);

    /**
     * 查询所有收藏
     * @return
     */
    List<Item> findCllect();

    /**
     * 用户回显
     * @return
     */
    User showUser(String name);
}
