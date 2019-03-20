package cn.itcast.core.controller.order;

import cn.itcast.core.pojo.order.Order;
import cn.itcast.core.service.order.OrderManagerService;
import cn.itcast.core.utils.excel.ExcelUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/orderManager")
public class OrderManager {
    @Reference
    OrderManagerService orderManagerService;

    /**
     * 订单导出excel表格
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/createExcel.do")
    public  void createExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取数据
        List<Order> orderList = orderManagerService.findAll();
        //excel文件名
        String fileName = "订单信息表"+System.currentTimeMillis()+".xls";
        //工作表名称
        String sheetName = "运营商订单表";
        //工作表表头字段
        String[] title = {"订单编号","商家id","用户id","总金额"};
        List<Object[]> dataList = new ArrayList<>();
        //创建内容
        for(int i = 0;i < orderList.size();i++){
            //将内容按顺序赋给对应的列对象
            Order order = orderList.get(i);
            Object[] obj = new Object[title.length];
            obj[0] = (order.getOrderId() == null) ? "无数据":order.getOrderId().toString();
            obj[1] = (order.getSellerId() == null) ? "无数据":order.getSellerId().toString();
            obj[2] = (order.getUserId() == null) ? "无数据":order.getUserId().toString();
            obj[3] = (order.getPayment() == null) ? "无数据":order.getPayment().toString();
            dataList.add(obj);
            obj = null;
        }
        //调用Excel工具类
        ExcelUtils excelUtils = new ExcelUtils();
        excelUtils.exportExcel(fileName,title,dataList,fileName,response);
    }
}
