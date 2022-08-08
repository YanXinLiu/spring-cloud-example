package com.yanxin.credit.utils;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class BeanUtils {

    /**
     * 方法说明：将bean转化为另一种bean实体
     * 
     * @param object
     * @param entityClass
     * @return
     */
    public static <T> T convertBean(Object object, Class<T> entityClass) {
        if(null == object) {
            return null;
        }
        return JSON.parseObject(JSON.toJSONString(object), entityClass);
    }

    /**
     * 方法说明：将字符串对象转换成具体对象
     * @param text
     * @param entityClass
     * @param <T>
     * @return
     */
    public static <T> T strToObject(String text, Class<T> entityClass) {
        if(null == text) {
            return null;
        }
        return JSON.parseObject(text, entityClass);
    }

    /**
     * 方法说明：对象转换
     * 
     * @param source	原对象
     * @param target	目标对象
     * @param ignoreProperties	排除要copy的属性
     * @return
     */
    public static <T> T copy(Object source, Class<T> target, String...ignoreProperties){
        T targetInstance = null;
        try {
            targetInstance = target.newInstance();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        if(ArrayUtils.isEmpty(ignoreProperties)) {
            org.springframework.beans.BeanUtils.copyProperties(source, targetInstance);
        }else {
            org.springframework.beans.BeanUtils.copyProperties(source, targetInstance, ignoreProperties);
        }
        return targetInstance;

    }

    /**
     * 方法说明：对象转换(List)
     * 
     * @param list	原对象
     * @param target	目标对象
     * @param ignoreProperties	排除要copy的属性
     * @return
     */
    public static <T, E> List<T> copyList(List<E> list, Class<T> target, String...ignoreProperties){
        List<T> targetList = new ArrayList<>();
        if(CollectionUtils.isEmpty(list)) {
            return targetList;
        }
        for(E e : list) {
            targetList.add(copy(e, target, ignoreProperties));
        }
        return targetList;
    }

    /**
     * 方法说明：map转化为对象
     *
     * @param map
     * @param t
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static <T> T mapToObject(Map<String, Object> map, Class<T> t){
        try {
            return BeanUtil.mapToBean(map, t, false);
        }catch (Exception e){
            log.error("[error]: {}", "对象转换失败");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 方法说明：对象转化为Map
     *  
     * @param object
     * @return
     */
    public static Map<?, ?> objectToMap(Object object){
        return convertBean(object, Map.class);
    }

    /**
     * 方法说明: json字符串转集合对象
     */

    public static <T> List<T> coverToList(Object object, Class<T> entityClass) {

        if(null == object) {
            return null;
        }
        return JSONObject.parseArray(JSONObject.toJSONString(object), entityClass);
    }

    public static <T> List<T> coverToListStr(Object object, Class<T> entityClass) {

        if(null == object) {
            return null;
        }
        return JSONObject.parseArray(object.toString(), entityClass);
    }

}
