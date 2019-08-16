package com.cqzx.test;

import com.cqzx.comm.util.JavaBeanUtil;
import com.cqzx.comm.util.MD5Hash;
import com.cqzx.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;

/**
 * @Description:
 * @Author:
 * @Date:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EntityToMapTest {

    @Test
    public void entityToMapTest() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Customer customer = new Customer();
        //设置用户主键以及密码
        customer.setCustomerId(MD5Hash.UUIDCreate());
        customer.setCustomerPassword(MD5Hash.encodeByMd5(customer.getCustomerLoginname()+customer.getCustomerPassword()));
        customer.setCustomerRegisttime(new Date());
        customer.setCustomerState(1);
        customer.setCustomerLastlogtime(new Date());
        customer.setCustomerLoginname("13022326792");

        Map<String, Object> map = JavaBeanUtil.convertBeanToMap(customer);
    }
}
