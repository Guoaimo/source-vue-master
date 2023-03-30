package cn.source.web.controller.tool;

import cn.source.common.utils.SecurityUtils;

public class Test {
    public static void main(String[] args) {
        System.out.println(
                SecurityUtils.encryptPassword("123456")
        );

    }
}
