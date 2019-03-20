package cn.itcast.core.service.user;

import cn.itcast.core.dao.user.UserDao;
import cn.itcast.core.entity.PageResult;
import cn.itcast.core.pojo.user.User;
import cn.itcast.core.pojo.user.UserQuery;
import cn.itcast.core.utils.md5.MD5Util;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jms.*;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private JmsTemplate jmsTemplate;

    @Resource
    private Destination smsDestination;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private UserDao userDao;

    /**
     * 用户获取短信验证码
     *
     * @param phone
     */
    @Override
    public void sendCode(final String phone) {
        // 将获取短信验证码的数据发送到mq中
        // 手机号、验证码、签名、模板
        final String code = RandomStringUtils.randomNumeric(6);
        System.out.println("code:" + code);
        // 保存验证码
        redisTemplate.boundValueOps(phone).set(code);
        // 设置验证码的过期时间
        redisTemplate.boundValueOps(phone).expire(5, TimeUnit.MINUTES);
        jmsTemplate.send(smsDestination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                // 封装map消息体
                MapMessage mapMessage = session.createMapMessage();
                mapMessage.setString("phoneNumbers", phone);
                mapMessage.setString("signName", "阮文");
                mapMessage.setString("templateCode", "SMS_140720901");
                mapMessage.setString("templateParam", "{\"code\":\"" + code + "\"}");
                return mapMessage;
            }
        });
    }

    /**
     * 用户注册
     *
     * @param user
     * @param smscode
     */
    @Transactional
    @Override
    public void add(User user, String smscode) {
        // 校验验证码是否正确
        String code = (String) redisTemplate.boundValueOps(user.getPhone()).get();
        if (smscode != null && !"".equals(smscode) && smscode.equals(code)) {
            // 对密码加密
            String password = MD5Util.MD5Encode(user.getPassword(), null);
            user.setPassword(password);
            user.setStatus("0");
            user.setCreated(new Date());
            user.setUpdated(new Date());
            userDao.insertSelective(user);
        } else {
            throw new RuntimeException("输入的验证码不正确");
        }
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    @Override
    public List<User> findAll() {
        return userDao.selectByExample(null);
    }

    /**
     * 修改用户状态
     */
    @Transactional
    @Override
    public void updateStatus(Long id, String status) {
        User user = new User();
        user.setId(id);
        if (status.equals("1")) {
            user.setStatus("0");
        }
        if (status.equals("0")) {
            user.setStatus("1");
        }
        userDao.updateByPrimaryKeySelective(user);
    }

    /**
     * 分页查询
     *
     * @param page
     * @param rows
     * @param user
     * @return
     */
    @Override
    public PageResult search(Integer page, Integer rows, User user) {
        //利用分页助手实现分页, 第一个参数:当前页, 第二个参数: 每页展示数据条数
        PageHelper.startPage(page, rows);
        UserQuery userQuery = new UserQuery();
        UserQuery.Criteria criteria = userQuery.createCriteria();
        if (user != null) {
            if (user.getUsername() != null && !"".equals(user.getUsername())) {
                criteria.andNameLike("%" + user.getUsername() + "%");
            }
        }
        Page<User> userList = (Page<User>) userDao.selectByExample(userQuery);
        return new PageResult(userList.getTotal(), userList.getResult());
    }

    /**
     * 查询用户
     * @param username
     * @return
     */
    @Override
    public User findUsername(String username) {
        return userDao.selectByPrimaryUsername(username);
    }

    /**
     * 查询用户Status状态且修改活跃值
     * @param username
     * @return
     */
    @Override
    public boolean findStatus(String username) {
        User user = findUsername(username);
        if (user != null && !"".equals(user)){
            if ("0".equals(user.getStatus())){
                User users = new User();
                users.setId(user.getId());
                users.setExperienceValue(user.getExperienceValue()+1);
                users.setLastLoginTime(new Date());
                userDao.updateByPrimaryKeySelective(users);
                return true;
            }
        }
        return false;
    }

    /**
     * 根据ID查询用户
     * @param id
     * @return
     */
    @Override
    public User findOne(Long id) {
        return userDao.selectByPrimaryKey(id);
    }

}
