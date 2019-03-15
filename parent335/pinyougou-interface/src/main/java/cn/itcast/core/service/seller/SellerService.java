package cn.itcast.core.service.seller;

import cn.itcast.core.entity.PageResult;
import cn.itcast.core.pojo.seller.Seller;

public interface SellerService {

    /**
     * 商家的入驻申请
     * @param seller
     */
    public void add(Seller seller);

    /**
     * 待审核商家的列表查询
     * @param page
     * @param rows
     * @param seller
     * @return
     */
    public PageResult search(Integer page, Integer rows, Seller seller);

    /**
     * 回显商家
     * @param sellerId
     * @return
     */
    public Seller findOne(String sellerId);

    /**
     * 审核商家
     * @param sellerId
     * @param status
     */
    public void updateStatus(String sellerId, String status);
}
