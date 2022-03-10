package com.yanxin.admin.service;

import cn.hutool.core.date.DateUtil;
import org.junit.Test;

import java.time.*;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2022-02-21 10:56
 */
public class LocalDateTest {

    @Test
    public void recentMonthTest() {

        // 获取近6月
        for (int i = 1; i < 7; i++) {
            System.out.println(YearMonth.now().minusMonths(i));
        }
    }

    @Test
    public void linkedListTest() {

        List<Integer> linedList = new LinkedList<Integer>();
        // 获取近6月
        for (int i = 1; i < 2; i++) {
            linedList.add(i);
        }
        linedList.add(linedList.size(), linedList.get(0));
        linedList.remove(0);
        System.out.println(Arrays.toString(linedList.toArray()));
    }

    @Test
    public void monthTest() {

        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalTime localTime = LocalTime.now(defaultZoneId);
        System.out.println(localTime);

        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 23));

        System.out.println(localDateTime);
    }
}
