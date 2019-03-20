package cn.itcast.core.controller.address;

import cn.itcast.core.entity.Result;
import cn.itcast.core.pojo.address.Address;
import cn.itcast.core.service.address.AddressService;
import cn.itcast.core.service.address.AreasService;
import cn.itcast.core.service.address.CitiesService;
import cn.itcast.core.service.address.ProvincesService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Reference
    private AddressService addressService;


    /**
     * 根据用户查询他的所有收货地址
     * @return
     */
    @RequestMapping("/findAll.do")
    public List<Address> findAll(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return  addressService.findAddressList(username);
    }

    /**
     * 根据用户新增一个地址
     * @param address
     * @return
     */
    @RequestMapping("/insert.do")
    public Result insert(@RequestBody Address address){
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        address.setUserId(userId);
        List<Address> addressList = addressService.findAddressList(address.getUserId());
        if(addressList == null || addressList.size() == 0){
            address.setIsDefault("1");
        }else {
            address.setIsDefault("0");
        }
        try {
            addressService.insert(address);
            return new Result(true,"添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"添加失败");
        }
    }
    /**
     * 根据地址id更新
     * @param address
     * @return
     */
    @RequestMapping("/update.do")
    public Result update(@RequestBody Address address){
        try {
            addressService.update(address);
            return new Result(true,"更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"更新失败");
        }
    }
    /**
     * 根据地址id更新默认地址
     * @param id
     * @return
     */
    @RequestMapping("/setDefaultAddress.do")
    public Result setDefaultAddress(Long id){
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        try {
            addressService.setDefaultAddress(userId,id);
            return new Result(true,"设置默认成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"设置默认失败");
        }
    }

    /**
     * 根据地址id查询当前地址对象
     * @param id
     * @return
     */
    @RequestMapping("/findOne.do")
    public Address findOne(Long id){
        return addressService.findOne(id);
    }

    /**
     * 根据地址id删除
     * @param id
     * @return
     */
    @RequestMapping("/delete.do")
    public Result delete(Long id){
        try {
            addressService.delete(id);
            return new Result(true,"删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }

}
