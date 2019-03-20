package cn.itcast.core.git;

import org.apache.poi.hssf.usermodel.*;

import java.io.UnsupportedEncodingException;

public class ExcelDownLoad {
    public static void main(String[] args) {
        //excel文件名
        String fileName = "G:\\表格\\订单信息表"+System.currentTimeMillis()+".xls";
        //工作表名称
        String sheetName = "运营商订单表";
        //工作表表头字段
        String[] title = {"订单编号","商家id","用户id","总金额"};
        //内容
        String[][] values = {{"1","A","a","100"},{"2","B","b","200"},{"1","C","c","300"},{"1","D","d","400"}};
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();

        //创建工作表
        HSSFSheet sheet = wb.createSheet(sheetName);

        //创建表头
        HSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();

        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        //声明列对象
        HSSFCell cell = null;

        //创建标题
        for(int i=0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }

        //创建内容
        for(int i=0;i<values.length;i++){
            row = sheet.createRow(i + 1);
            for(int j=0;j<values[i].length;j++){
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(values[i][j]);
            }
        }

        try {
            fileName = new String(fileName.getBytes(),"ISO8859-1");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }





    }


}
