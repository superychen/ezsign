package com.cqzx.auth;

import com.cqzx.comm.util.sealUtil.Sealfun;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:
 * @Author:
 * @Date:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SealTest {

    @Test
    public void personSeal() throws Exception {
        Sealfun sealfun = new Sealfun();
//        CommonUtil.getStrObjCvert(data.get("fullName"));
        String name = "杨晨";
        int fontSize = 0;
        if(name.length() == 3){
            fontSize = 30;
        }
        if(name.length() == 2 || name.length() == 4){
            fontSize = 40;
        }
        byte[] sealBytes = sealfun.imagePersonSeal(name, fontSize);
    }

    @Test
    public void companySeal() throws Exception {
        Sealfun sealfun = new Sealfun();
        byte[] sealBytes = sealfun.imageCompanySeal("companyName","");

    }
}
