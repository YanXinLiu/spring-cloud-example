package com.yanxin.credit.mapstruct;

import com.yanxin.common.base.BaseMapper;
import com.yanxin.credit.dto.CarDTO;
import com.yanxin.credit.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarStruct extends BaseMapper<CarDTO, Car> {
}
