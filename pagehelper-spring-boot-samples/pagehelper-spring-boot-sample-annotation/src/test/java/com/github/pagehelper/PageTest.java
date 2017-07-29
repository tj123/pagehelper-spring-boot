package com.github.pagehelper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.pagehelper.mapper.CountryMapper;

/**
 * Created by TJ on 2017/7/28.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PageTest {

    @Autowired
    CountryMapper mapper;

    @Test
    public void test() throws Exception {


        System.out.println(mapper.findAll());

    }
}
