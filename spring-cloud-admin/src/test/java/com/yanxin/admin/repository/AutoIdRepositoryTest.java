package com.yanxin.admin.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"spring.profiles.active=dev"})
public class AutoIdRepositoryTest {

    @Autowired
    private AutoIdRepository autoIdRepository;

    @Test
    public void getMaxId() {

        int maxNum = autoIdRepository.getMaxId();
        System.out.println(maxNum);

    }
}