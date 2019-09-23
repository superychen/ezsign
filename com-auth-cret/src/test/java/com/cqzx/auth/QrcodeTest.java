package com.cqzx.auth;

import com.cqzx.comm.util.QRCodeUtil;
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
public class QrcodeTest {

    @Test
    public void qrCodeTest(){
        QRCodeUtil.zxingCodeCreate("http://192.168.227.39:8080/#/personalAuthenInfo", "D:\\mywork\\rpc\\",500,null);
    }
}
