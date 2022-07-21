package com.yanxin.credit.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanxin.credit.entity.Car;
import com.yanxin.credit.entity.Member;
import com.yanxin.credit.mapper.CarMapper;
import com.yanxin.credit.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements ICarService {

    @Autowired
    private CarMapper carMapper;


    public List<Car> selectList(Date recycleTime) {

        QueryWrapper<Member> query = new QueryWrapper<>();

        return Collections.emptyList();
    }

    public int insertGoods(Car car) {

        return carMapper.insert(car);
    }

    public int deleteGoods(Car car) {
        return 0;
    }

    public int updateGoods(Car car) {
        return 0;
    }
}
