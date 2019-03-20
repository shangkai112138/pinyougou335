package cn.itcast.core.service.brand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BrandManagerService {
    String uploadExcel(HttpServletRequest request,HttpServletResponse response);
}
