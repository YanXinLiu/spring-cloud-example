package com.yanxin.admin.service.impl;

import com.yanxin.admin.domain.LdapUser;
import com.yanxin.admin.domain.User;
import com.yanxin.admin.dto.GoodsDTO;
import com.yanxin.admin.dto.LoginUserDTO;
import com.yanxin.admin.repository.LdapUserRepository;
import com.yanxin.admin.repository.UserRepository;
import com.yanxin.admin.service.UserService;
import com.yanxin.admin.service.feign.GoodsServiceFeignApi;
import com.yanxin.common.base.ResultBody;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2020-12-24 14:18
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LdapUserRepository ldapUserRepository;

    @Autowired
    private LdapTemplate ldapTemplate;

    @Autowired
    private GoodsServiceFeignApi goodsServiceFeignApi;

    @Override
    @Transactional(readOnly = true)
    public User selectByName(String username) {

        List<LdapUser> users = ldapTemplate.find(LdapQueryBuilder.query()
                .where("cn").is("wangwu")
                .or("sAMAccount1Name").is("ww")
                .or("userPrincipalName").is("w1w@jktest.cn"), LdapUser.class);

        /*return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("未找到用户:" + username));*/
        return User.builder()
                .username("root").build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Caching(
            put = {@CachePut(value = "userCache", key = "#user.id")},
            evict = {@CacheEvict(value = "userListCache", allEntries = true)}
    )
    @GlobalTransactional(name = "goods_tx_group")
    public User insertUser(User user) {
        userRepository.save(user);
        GoodsDTO goodsDTO = new GoodsDTO();
        goodsDTO.setName(user.getUsername());
        ResultBody resultBody = goodsServiceFeignApi.addGoods(goodsDTO);
        log.info("id: {}", user.getId());
        return user;

    }

    @Override
    @Cacheable(value = "userCache", key = "#id")
    public User getUserById(String id) {

        log.info("[BIZ] 未使用缓存");
        return userRepository.findById(NumberUtils.toLong(id))
                .orElseThrow(() -> new IllegalArgumentException("未查找到该用户: " + id));

    }

    @Override
    @Cacheable(value = "userListCache")
    public List<User> getAll() {

        return userRepository.findAll();
    }

    @Override
    public Boolean login(LoginUserDTO loginUserDTO) {

        Optional<LdapUser> opt = ldapUserRepository.findByCnAndUserPrincipalName("wangwu", "ww@jktest.cn");
        LdapUser user = opt.get();
        EqualsFilter filter = new EqualsFilter("sAMAccountName", user.getSAMAccountName());
        return ldapTemplate.authenticate("", filter.toString(), "123qweASD");
    }
}
