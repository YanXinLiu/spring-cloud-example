package com.yanxin.admin.service;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2021-05-24 11:44
 */
public class StringTest {

    @Test
    public void testContains() {

        String charStr = "commit,society,human";

        String value = StrUtil.subBefore(charStr, ",", false);

        System.out.println(value);

    }

    @Test
    public void replaceTest() {

        // target ip:192.136,182.394
        List<String> result = new ArrayList<>();
        String filed = "ip,b";
        // String charStr = "ip:192.168.3.58,192.168.3.51,b:234";
        String charStr = "ip:192.168.3.58,192.168.3.51,b:234";

        List<String> esList = Stream.of(filed.split(",")).collect(Collectors.toList());
        for (String str : esList) {
            String value = StrUtil.subAfter(charStr, str, false);
            String subValue = StrUtil.subAfter(value, ",", true);
            if (StrUtil.isNotEmpty(subValue) && StrUtil.contains(subValue, ':')) {

                System.out.println("final: " + str + StrUtil.subBefore(value, "," + subValue, false));
            } else {
                System.out.println("final: " + str + value);
            }
        }
    }

    @Test
    public void listStrTest() {

        String filed = "ip,b";
        List<String> valueList = Stream.of(filed.split(",")).collect(Collectors.toList());
        System.out.println(JSONObject.toJSONString(valueList));

    }
}
