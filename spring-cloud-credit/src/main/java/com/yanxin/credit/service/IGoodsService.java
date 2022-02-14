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

    public List<Goods> selectList(String name);

    public int insertGoods(Goods goods);

    public int deleteGoods(Goods goods);

    public int updateGoods(Goods goods);
}
