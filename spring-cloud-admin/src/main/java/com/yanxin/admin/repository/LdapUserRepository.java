package com.yanxin.admin.repository;

import com.yanxin.admin.domain.LdapUser;
import org.springframework.data.repository.CrudRepository;

import javax.naming.Name;
import java.util.Optional;

/**
 * @program spring-cloud-example
 * @description:
 * @author: 、
 * @create: 2020-12-24 17:07
 */
public interface LdapUserRepository extends CrudRepository<LdapUser, Name> {

    Optional<LdapUser> findByCnAndUserPrincipalName(String cn, String principalName);

}
