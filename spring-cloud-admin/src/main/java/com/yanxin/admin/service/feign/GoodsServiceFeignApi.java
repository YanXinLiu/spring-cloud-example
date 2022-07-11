package com.yanxin.admin.service.feign;

import com.yanxin.admin.dto.GoodsDTO;
import com.yanxin.common.base.ResultBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "cloud-credit")
public interface GoodsServiceFeignApi {

    @PostMapping("/goods/add")
    ResultBody addGoods(@RequestBody GoodsDTO goods);
}
