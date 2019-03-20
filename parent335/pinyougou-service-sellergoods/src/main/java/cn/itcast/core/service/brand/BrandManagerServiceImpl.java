package cn.itcast.core.service.brand;

import cn.itcast.core.dao.good.BrandDao;
import cn.itcast.core.pojo.good.Brand;
import cn.itcast.core.utils.excel.UploadExcelUtils;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class BrandManagerServiceImpl implements BrandManagerService{
    @Resource
    BrandDao brandDao;

    @Override
    public String uploadExcel(HttpServletRequest request, HttpServletResponse response) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        MultipartFile file = multipartRequest.getFile("file");
        if(file.isEmpty()){
            try {
                throw new Exception("文件不存在！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        InputStream in =null;
        try {
            in = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<List<Object>> listob = null;
        try {
            listob = new UploadExcelUtils().getBankListByExcel(in,file.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < listob.size(); i++) {
            /*   List<Object> lo = listob.get(i);
               if (lo.get(i)=="") {
                    continue;
                }*/
            System.out.println(listob.get(i));

        }
        for (int i = 0; i < listob.size(); i++) {
            List<Object> lo = listob.get(i);
            Brand vo = new Brand();
            Brand j = null;

            try {
                j = brandDao.selectByPrimaryKey(Long.valueOf(String.valueOf(lo.get(0))));
            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                System.out.println("数据库中无该条数据，新增");

            }
            vo.setId(Long.valueOf(String.valueOf(lo.get(0))));
            vo.setName(String.valueOf(lo.get(1)));
            vo.setFirstChar(String.valueOf(lo.get(2)));
            vo.setStatus(Long.valueOf(String.valueOf(lo.get(3))));

            if(j == null)
            {

                brandDao.insert(vo);
                System.out.println("success");
            }
            else
            {
                brandDao.updateByPrimaryKey(vo);
            }

        }

        return "文件导入成功！";
    }
}
