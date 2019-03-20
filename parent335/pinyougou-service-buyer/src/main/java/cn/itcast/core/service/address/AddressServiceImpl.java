package cn.itcast.core.service.address;

import cn.itcast.core.dao.address.AddressDao;
import cn.itcast.core.pojo.address.Address;
import cn.itcast.core.pojo.address.AddressQuery;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService{

    @Resource
    private AddressDao addressDao;

    /**
     * 用户查询他自己的所有收货地址
     * @param userId
     * @return
     */
    @Override
    public List<Address> findAddressList(String userId) {
        AddressQuery addressQuery = new AddressQuery();
        addressQuery.createCriteria().andUserIdEqualTo(userId);
        return addressDao.selectByExample(addressQuery);
    }

    /**
     * 根据id删除当前登录人的地址
     * @param id
     * @return
     */
    @Override
    @Transactional
    public void delete(Long id) {
        addressDao.deleteByPrimaryKey(id);
    }

    /**
     * 根据用户新增一个地址
     * @return
     */
    @Override
    @Transactional
    public void insert(Address address) {
        addressDao.insert(address);
    }

    /**
     * 根据地址id查询当前地址对象
     * @param id
     * @return
     */
    @Override
    public Address findOne(Long id) {
        return addressDao.selectByPrimaryKey(id);
    }

    /**
     * 根据地址id更新
     * @param address
     * @return
     */
    @Override
    @Transactional
    public void update(Address address) {
        addressDao.updateByPrimaryKeySelective(address);
    }

    /**
     * 根据地址id更新默认地址
     * @param id
     * @return
     */
    @Transactional
    @Override
    public void setDefaultAddress(String userId,Long id) {
        //查询原来默认的地址，并更改为0
        AddressQuery addressQuery = new AddressQuery();
        addressQuery.createCriteria().andIsDefaultEqualTo("1").andUserIdEqualTo(userId);
        List<Address> addresses = addressDao.selectByExample(addressQuery);
        if(addresses != null && addresses.size() > 0){
            for (Address address : addresses) {
                address.setIsDefault("0");
                addressDao.updateByPrimaryKeySelective(address);
            }
        }
        //更改当前为默认
        Address one = findOne(id);
        one.setIsDefault("1");
        addressDao.updateByPrimaryKeySelective(one);
    }
}
