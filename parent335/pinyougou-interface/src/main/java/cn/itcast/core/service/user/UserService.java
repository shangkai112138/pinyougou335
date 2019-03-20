package cn.itcast.core.service.user;

import cn.itcast.core.entity.PageResult;
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

    /**
     * 查询所有用户
     * @return
     */
    List<User> findAll();

    /**
     * 修改用户状态
     */
    void updateStatus(Long id, String status);

    /**
     * 分页查询
     * @param user
     * @param page
     * @param rows
     * @return
     */
    PageResult search(Integer page, Integer rows, User user);

    /**
     * 查询用户
     * @param username
     * @return
     */
    public User findUsername(String username);

    /**
     * 查询用户Status状态
     * @param username
     * @return
     */
    boolean findStatus(String username);

    /**
     * 根据ID查询用户
     * @param id
     * @return
     */
    User findOne(Long id);

    User findOne(String username);
}
