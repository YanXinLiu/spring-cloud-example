package com.yanxin.credit.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanxin.credit.entity.Goods;
import com.yanxin.credit.mapper.GoodsMapper;
import com.yanxin.credit.service.IGoodsService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2022-02-08 18:38
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    private GoodsMapper goodsMapper;

    public GoodsServiceImpl(GoodsMapper goodsMapper) {
        this.goodsMapper = goodsMapper;
    }

    @Override
    public List<Goods> selectList(String name) {
        QueryWrapper<Goods> query = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(name)) {
            query.eq("name", name);
        }
        return goodsMapper.selectList(query);
    }

    @Override
    public int insertGoods(Goods goods) {
        goods.setCreateTime(new Date());
        goods.setUpdateTime(new Date());
        return goodsMapper.insert(goods);
    }

    @Override
    public int deleteGoods(Goods goods) {
        return goodsMapper.deleteById(goods);
    }

    @Override
    public int updateGoods(Goods goods) {
        goods.setUpdateTime(new Date());
        return goodsMapper.updateById(goods);
    }

}
