package com.yanxin.admin.repository;


import com.yanxin.admin.domain.AutoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AutoIdRepository extends JpaRepository<AutoId, Long> {

    @Query(value = "SELECT MAX(max_id) FROM auto_id", nativeQuery = true)
    int getMaxId();

    AutoId findByMaxId(long maxId);

}
