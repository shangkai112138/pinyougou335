package cn.itcast.core.controller.pieChart;

import cn.itcast.core.service.piechart.PieChartDataService;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/pieChart")
public class pieCharController {

    @Reference
    private PieChartDataService pieChartDataService;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        }

    @RequestMapping("/PieChartData.do")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        List list = pieChartDataService.PieChartData();
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(list));
        response.getWriter().write(array.toJSONString());
    }

}
