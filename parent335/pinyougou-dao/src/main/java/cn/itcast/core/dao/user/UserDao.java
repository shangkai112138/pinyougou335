package cn.itcast.core.dao.user;

import cn.itcast.core.pojo.user.User;
import cn.itcast.core.pojo.user.UserQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserDao {
    int countByExample(UserQuery example);

    int deleteByExample(UserQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserQuery example);

    User selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserQuery example);

    int updateByExample(@Param("record") User record, @Param("example") UserQuery example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 根据名字来查询
     * @param name
     * @return
     */
    User findUserByName(String name);

    User selectByUsername(String  username);

    // 查询
    User selectByPrimaryUsername(String username);
}