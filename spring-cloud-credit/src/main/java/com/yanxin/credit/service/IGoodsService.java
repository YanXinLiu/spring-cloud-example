package com.yanxin.credit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yanxin.credit.entity.Goods;
import com.yanxin.credit.entity.Member;

import java.util.List;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2022-02-08 18:33
 */
public interface IGoodsService extends IService<Goods> {

    List<Goods> selectList(String name);

    int insertGoods(Goods goods);

    int deleteGoods(Goods goods);

    int updateGoods(Goods goods);
}
