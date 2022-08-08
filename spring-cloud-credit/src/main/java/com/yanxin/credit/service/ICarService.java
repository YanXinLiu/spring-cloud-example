package com.yanxin.credit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yanxin.credit.dto.CarDTO;
import com.yanxin.credit.entity.Car;

import java.util.List;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2022-02-08 18:33
 */
public interface ICarService extends IService<Car> {

    Car insertCar(CarDTO car);

    int dropCar(int carId);

    List<Car> listBySelective();

}
