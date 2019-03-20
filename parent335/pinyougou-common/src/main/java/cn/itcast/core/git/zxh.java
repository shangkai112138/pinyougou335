package cn.itcast.core.git;

import cn.itcast.core.utils.md5.MD5Util;

public class zxh {
    public static void main(String[] args) {
        System.out.println("我是张学宏！");
        System.out.println("我是汪鹏！");
        System.out.println("我是aa！");
        System.out.println("提交测试");
        System.out.println("我是杨德刚");
        System.out.println("我是尚楷");
        String password = MD5Util.MD5Encode("123456", null);
        System.out.println(password);
    }
}
