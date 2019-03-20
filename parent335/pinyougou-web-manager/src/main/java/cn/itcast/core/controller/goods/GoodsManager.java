package cn.itcast.core.controller.goods;

import cn.itcast.core.pojo.good.Goods;
import cn.itcast.core.service.goods.GoodsManagerService;
import cn.itcast.core.utils.excel.ExcelUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/goodsManager")
public class GoodsManager {
    @Reference
    GoodsManagerService goodsManagerService;

    /**
     * 订单导出excel表格
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/createExcel.do")
    public void createExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取数据
        List<Goods> goodsList = goodsManagerService.findAll();
        //excel文件名
        String fileName = "用户商品信息表" + System.currentTimeMillis() + ".xls";
        //工作表名称
        String sheetName = "运营商用户表";
        //工作表表头字段
        String[] title = {"商品id", "商品名称", "商品价格", "一级分类", "二级分类", "三级分类", "状态", "操作"};
        List<Object[]> dataList = new ArrayList<>();
        //创建内容
        for (int i = 0; i < goodsList.size(); i++) {
            //将内容按顺序赋给对应的列对象
            Goods goods = goodsList.get(i);
            Object[] obj = new Object[title.length];
            obj[0] = (goods.getId() == null) ? "无数据" : goods.getId().toString();
            obj[1] = (goods.getGoodsName() == null) ? "无数据" : goods.getGoodsName().toString();
            obj[2] = (goods.getPrice() == null) ? "无数据" : goods.getPrice().toString();
            obj[3] = (goods.getCategory1Id() == null) ? "无数据" : goods.getCategory1Id().toString();
            obj[4] = (goods.getCategory2Id() == null) ? "无数据" : goods.getCategory2Id().toString();
            obj[5] = (goods.getCategory3Id() == null) ? "无数据" : goods.getCategory3Id().toString();
            obj[6] = (goods.getAuditStatus() == null) ? "无数据" : goods.getAuditStatus().toString();
            obj[7] = (goods.getIsDelete() == null) ? "无数据" : goods.getIsDelete().toString();
            dataList.add(obj);
            obj = null;
        }
        //调用Excel工具类
        ExcelUtils excelUtils = new ExcelUtils();
        excelUtils.exportExcel(fileName, title, dataList, fileName, response);
    }
}