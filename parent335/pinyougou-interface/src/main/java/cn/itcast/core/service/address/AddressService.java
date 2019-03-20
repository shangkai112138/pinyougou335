package cn.itcast.core.service.address;

import cn.itcast.core.pojo.address.Address;

import java.util.List;

public interface AddressService {

    /**
     * 获取当前登录人的地址列表
     * @param userId
     * @return
     */
    public List<Address> findAddressList(String userId);

    /**
     * 根据id删除当前登录人的地址
     * @param id
     * @return
     */
    void delete(Long id);

    /**
     * 根据用户新增一个地址
     * @param address
     */
    void insert(Address address);

    /**
     * 根据地址id查询当前地址对象
     * @param id
     * @return
     */
    Address findOne(Long id);

    /**
     * 根据地址id更新
     * @param address
     * @return
     */
    void update(Address address);

    /**
     * 根据地址id更新默认地址
     * @param id
     * @return
     */
    void setDefaultAddress(String  userId,Long id);
}
