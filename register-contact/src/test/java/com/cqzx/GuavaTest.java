package com.cqzx;

import com.google.common.base.CaseFormat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description: 测试将驼峰命名法转为数据库中的下划线
 * @Author: cqyc
 * @Date: 2019-7-25
 *
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class GuavaTest {
    @Test
    public void testGuava(){
        String res = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,"customer_password=杨晨Kvkwx8tejE2re8YymTb4/w");
        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "customer_password=杨晨Kvkwx8tejE2re8YymTb4/w"));
        System.out.println(res);
    }

}
