package com.yanxin.credit.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanxin.credit.dto.CarDTO;
import com.yanxin.credit.entity.Car;
import com.yanxin.credit.mapper.CarMapper;
import com.yanxin.credit.mapstruct.CarStruct;
import com.yanxin.credit.service.ICarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements ICarService {

    private final CarMapper carMapper;

    private final CarStruct carStruct;

    public List<Car> selectByRecycleTime(Date startTime, Date endTime) {

        QueryWrapper<Car> query = new QueryWrapper<>();
        query.ge("recycle_time", startTime).le("recycle_time", endTime);
        return carMapper.selectList(query);
    }

    @Override
    public Car insertCar(CarDTO carDto) {

        Car car = new Car();
        BeanUtil.copyProperties(car, carDto);
        car.setCreateTime(new Date());
        saveOrUpdate(car);
        return car;
    }

    @Override
    public int dropCar(int carId) {
        return 0;
    }

    @Override
    public List<Car> listBySelective() {
        return carMapper.selectList(null);
    }
}
